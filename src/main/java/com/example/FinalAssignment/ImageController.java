package com.example.FinalAssignment;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ImageController {

    private final List<Image> image = new ArrayList<>();
    private final Path usersFile = Path.of("./users.json");


    @GetMapping("images/{imageId}/file")
    public String GetImagesFile(@PathVariable String imageID) throws Exception {
         Image image = DeleteImage(imageID);
        return image.getS3URL();

    }

    @GetMapping("images/{imageId}/metadata")
    public List<String> GetImagesMeta(@PathVariable String imageID) throws Exception {
        Image image = DeleteImage(imageID);
         List<String> images = new ArrayList<>();
        images.add(image.getFileName());
        images.add(image.getUploader());
        images.add(image.getS3URL());

        return images;

    }

    @GetMapping("images")
    public List<Image> GetImages() {

        return this.image;

    }

    @PostMapping("images")
    public Image AddImages(@RequestBody
                           @PathVariable Image imagePost
    ) {

        String uuid = UUID.randomUUID().toString();
        Long createdDate = System.currentTimeMillis();

        Image imageUser = new Image(uuid, createdDate, imagePost.getS3URL(), imagePost.getFileName(), imagePost.getUploader());
        image.add(imageUser);

        return imageUser;
    }

    @DeleteMapping("images/{imageId}")
    public Image DeleteImage(
            @PathVariable String id
    ) throws Exception {
        for (Image images : this.image) {
            if (images.getID().equals(id)) {
                return images;
            }
        }
        throw new Exception("Could not find user with ID " + id);
    }

}
