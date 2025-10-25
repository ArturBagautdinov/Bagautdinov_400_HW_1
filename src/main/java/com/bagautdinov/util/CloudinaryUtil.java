package com.bagautdinov.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> config = new HashMap<>();
            config.put("cloud_name", "dyidz3kse");
            config.put("api_key", "326143749737947");
            config.put("api_secret", "d_3qClY0jkg4lbsql7aJLjUZ_k4");
            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }
}
