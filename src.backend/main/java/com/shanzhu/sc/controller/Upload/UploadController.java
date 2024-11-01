package com.shanzhu.sc.controller.Upload;

import com.shanzhu.sc.domain.Upload;
import com.shanzhu.sc.service.Upload.UploadService;
import com.shanzhu.sc.utils.ResourceUtil;
import com.shanzhu.sc.utils.UserLoginToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import static java.lang.Integer.parseInt;


/**
 * 上传文件 控制层
 *
 * @author: ShanZhu
 * @date: 2024-01-08
 */
@RestController
@RequestMapping("/api/sms/upload")
public class UploadController {

    @Resource
    private UploadService uploadService;

    @PostMapping("/headImg")
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                // 储存位置
                String staticDir = ResourceUtil.getPath();

                // 图片名
                String ImgName = file.getOriginalFilename();

                String uid = UUID.randomUUID().toString();
                assert ImgName != null;
                // 获取后缀名
                String str = ImgName.substring(ImgName.lastIndexOf("."));
                // 重定义文件名
                String newName = uid + str;

                // 图片存储地址
                Path path = Paths.get(staticDir + newName);
                // 写入文件
                Files.write(path, bytes);
                String imgUrl = "/files/" + newName;

                String userId = request.getParameter("id");
                int level = parseInt(request.getParameter("level"));
                Upload upload = new Upload();
                upload.setUserId(userId);
                upload.setLevel(level);
                upload.setUrl(imgUrl);
                uploadService.upload(upload);

                // url去除"sms"
                return imgUrl;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @GetMapping("/getHeadImg")
    @UserLoginToken
    public String getAdminList(@RequestParam Map<String, Object> condition, HttpServletRequest httpServletRequest) {
        return uploadService.getHeader(condition);
    }
}
