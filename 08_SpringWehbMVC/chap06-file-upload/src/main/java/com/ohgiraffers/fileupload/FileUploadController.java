package com.ohgiraffers.fileupload;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    private ResourceLoader resourceLoader;

    @Autowired
    public FileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    // MultipartFile
    //

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile, String singleFileDescription, Model model) throws IOException {

        // 파일 저장 경로를 설정한다
        Resource resource =resourceLoader.getResource("classpath:static/img/single");

        String filePath = null;

        // 만약 해당 경로가 없다면
        if(!resource.exists()) {
            String root = "src/main/resources/static/img/single";

            File file = new File(root);
            file.mkdirs();
//            D:/class/gangnam/spring/src/ressouce/static/img : 드라이브부터 시작되는 게 절대 경로, 어떤 위치에 있더라도 찾아서 갈 수 있다
//                            spring/src/ressouce/static/img : 내가 있는 걸 기준으로 시작되는 게 상대경로


            filePath = file.getAbsolutePath();

        }else {
            filePath = resourceLoader.getResource("classpath:static/img/single").getFile().getAbsolutePath();
        }
        String originalFilename = singleFile.getOriginalFilename(); // 파일의 원본이름을 읽어온다
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".")); // 확장자 분리
        // lastIndexOf 점을 기준으로 점 이후의 것만 가져옴. 점을 기준으로 잘라줌,

        String saveName = UUID.randomUUID().toString().replace("-","") + ext;

        try{
            singleFile.transferTo(new File(filePath + "/" + saveName));
            model.addAttribute("message", "파일 업로드 성공");
            model.addAttribute("img", "static/img/single/" + saveName);
        }catch (Exception exception){
            exception.printStackTrace();
            model.addAttribute("message","파일업로드 실패");
        }

        return "result";

    }


    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multipartFiles,
                                  String multiFileDescription, Model model) throws IOException {

        Resource resource =resourceLoader.getResource("classpath:static/img/multi");
        String filePath = null;

       if(!resource.exists()) {
           String root = "src/main/resources/static/img/multi";
           File file = new File(root);
           file.mkdirs();
           filePath = file.getAbsolutePath();
       } else {
           filePath = resourceLoader.getResource("classpath:static/img/multi").getFile().getAbsolutePath();
       }

       List<FileDTO> files = new ArrayList<>();
       List<String> saveFiles = new ArrayList<>();
       try {
           for(MultipartFile file : multipartFiles) {

               String originalFilename = file.getOriginalFilename();
               String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
               String savedName = UUID.randomUUID().toString().replace("-","") + ext;

               files.add(new FileDTO(originalFilename, savedName, filePath, multiFileDescription));

               file.transferTo(new File(filePath + "/" + savedName));
               saveFiles.add("static/img/multi/" + savedName);

           }

           model.addAttribute("message", "파일 업로드 성공!");
           model.addAttribute("imgs", saveFiles);

       }catch (Exception exception){
           exception.printStackTrace();

           // 파일을 지우는 로직 -> 실패했을 때 모든 파일을 다 지우고 다시 요청하도록 하는 처리.
           for(FileDTO file: files) {
               new File(filePath + "/" +file.getSaveName()).delete();
           }
           model.addAttribute("message","파일 업로드 실패");


       }

       return "result";
    }


}
