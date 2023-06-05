package com.demo;


import com.spring.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
@Controller
public class CartoonController{

    @Autowired
    private CartoonService service;

    public CartoonController() {
        System.out.println("CartoonController");
    }

    @RequestMapping("/count")
    @ResponseBody
    public String count(Map<String, String> param, Map<String, Object> model) {
        return "count +" + service.count();
    }

    @RequestMapping("/list")
    public String list(Map<String, String> param, Map<String, Object> model) {
        List<CartoonDto> cartoons = service.select();
        model.put("count", cartoons.size());
        for(int i = 0; i < cartoons.size(); i++) {
            model.put("idx" + i, cartoons.get(i).getIdx());
            model.put("title" + i, cartoons.get(i).getTitle());
            model.put("image" + i, cartoons.get(i).getImage());
        }
        return  "list";
    }

    @RequestMapping("/read")
    public String read(Map<String, String> param, Map<String, Object> model) {
        int idx = Integer.parseInt(param.get("idx"));
        CartoonDto cartoon = service.read(idx);
        model.put("idx", cartoon.getIdx());
        model.put("title", cartoon.getTitle());
        model.put("image", cartoon.getImage());
        model.put("content", cartoon.getContent());
        return  "read";
    }



    @RequestMapping("/delete")
    public String delete(Map<String , String> param, Map<String, Object> model) {
        int idx = Integer.parseInt(param.get("idx"));
        service.delete(idx);
        return "delete_list";
    }

    @RequestMapping("/insertForm")
    public String insertForm(Map<String, String> param, Map<String, Object> model) {
        return "insertForm";
    }

    @RequestMapping("/insert")
    public String  insert(Map<String, String> param, Map<String, Object> model) {
        Integer idx = Integer.parseInt(URLDecoder.decode(param.get("idx"), StandardCharsets.UTF_8));
        String image = URLDecoder.decode(param.get("image"), StandardCharsets.UTF_8);
        String title = URLDecoder.decode(param.get("title"), StandardCharsets.UTF_8);
        String content = URLDecoder.decode(param.get("content"), StandardCharsets.UTF_8);
        CartoonDto ct = new CartoonDto(idx, title, image, content);
        service.insert(ct);
        System.out.println(ct);

        return "insertList";
    }
    @RequestMapping("/update")
    public String  update(Map<String, String> param, Map<String, Object> model) {
        int idx = Integer.parseInt(param.get("idx"));

        String image = URLDecoder.decode(param.get("image"), StandardCharsets.UTF_8);
        String title = URLDecoder.decode(param.get("title"), StandardCharsets.UTF_8);
        String content = URLDecoder.decode(param.get("content"), StandardCharsets.UTF_8);
        CartoonDto ct = new CartoonDto(idx, title, image, content);
        service.update(ct);

        return "updateList";
    }
    @RequestMapping("/insertList")
    public String insertList(Map<String, String> param, Map<String, Object> model) {
        return "insertList";
    }

     @RequestMapping("/updateForm")
    public String updateForm(Map<String , String> param, Map<String, Object> model) {
         int idx = Integer.valueOf(param.get("idx"));
         CartoonDto cartoon = service.read(idx);
         model.put("idx", Integer.parseInt(URLDecoder.decode(String.valueOf(cartoon.getIdx()), StandardCharsets.UTF_8)));
         model.put("title", URLDecoder.decode(cartoon.getTitle(), StandardCharsets.UTF_8));
         model.put("image", URLDecoder.decode(cartoon.getImage(), StandardCharsets.UTF_8));
         model.put("content", URLDecoder.decode(cartoon.getContent(), StandardCharsets.UTF_8));

        return "updateForm";
    }




    @RequestMapping("/search")
    public String search(Map<String, String> param, Map<String, Object> model) throws UnsupportedEncodingException {
        String decodedTitle = URLDecoder.decode(param.get("title"), "UTF-8");
//        System.out.println(name);
//        List<MovieDto> searchMovie = service.search(s_idx);
//        System.out.println("searchMovie = " + searchMovie);
//        for(int i=0;i<searchMovie.size();i++) {
//            model.put("idx " + i, searchMovie.get(i).getIdx());
//            model.put("title " + i, searchMovie.get(i).getTitle());
//            model.put("image " + i, searchMovie.get(i).getImage());
//        }
        CartoonDto cartoon = service.search(decodedTitle);
        model.put("idx", cartoon.getIdx());
        model.put("title", cartoon.getTitle());
        model.put("image", cartoon.getImage());
        return "search";
    }

}
