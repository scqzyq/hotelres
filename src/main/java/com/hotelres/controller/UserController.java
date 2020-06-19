package com.hotelres.controller;

import com.hotelres.bean.User;
import com.hotelres.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<User> findAll(String username) {
        System.out.println(username);
        List<User> list = userService.findAll(username);
        System.out.println(list);
        return list;
    }

    @RequestMapping("/checkIdcard")
    @ResponseBody
    public String checkIdcard(String idcard) {
        User user = this.userService.findByIdcard(idcard);
//        System.out.println(user);
        String flag = "no";
        if (user == null) {
            return flag;
        } else {
            flag = "yes";
            return flag;
        }

    }

    @RequestMapping("/checkUsername")
    @ResponseBody
    public String checkUsername(String username) {
        User user = this.userService.findByUsername(username);
        System.out.println(user);
        String flag = "no";
        if (user == null) {
            return flag;
        } else {
            flag = "yes";
            return flag;
        }
    }

    @RequestMapping("/saveUser")
    public String saveUser(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));;
        System.out.println(user);
        user.setJiudianweizhi(5.0);
        user.setWeishengqingjie(5.0);
        user.setShebeisheshi(5.0);
        user.setFuwuzhiliang(5.0);
        userService.saveUser(user);
        return "forward:/login.jsp";
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String pas = DigestUtils.md5DigestAsHex(password.getBytes());
//            System.out.println(username + "--" + password);

            User u = userService.login(username, pas);
//            System.out.println(u.getUsername());
            if (u.getUsername().equals(username) && u.getPassword().equals(pas)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
            } else {
                request.setAttribute("error", 1);
                request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request, response);
            }
        } catch (Exception e) {

            request.setAttribute("error", 1);
            request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request, response);
        }
    }


    @RequestMapping("/checkUserInfo")
    @ResponseBody
    public String checkUserInfo(String idcard, String realname, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = this.userService.findByIdcardAndRealname(idcard, realname);
        System.out.println(user);
        if (user == null) {
            String msg = "yes";
            return msg;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            return "no";
        }
    }

    @RequestMapping("/updatePw")
    public void updatePw(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String password = request.getParameter("password");
        String pas = DigestUtils.md5DigestAsHex(password.getBytes())
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        this.userService.updatePassword(username, password);
        session.invalidate();
        request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request, response);
    }


    @RequestMapping("/updateInfo")
    public void updateInfo(String jiudianweizhi, String weishengqingjie, String shebeisheshi, String fuwuzhiliang, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double h1 = Double.parseDouble(jiudianweizhi);
        double s1 = Double.parseDouble(weishengqingjie);
        double f1 = Double.parseDouble(shebeisheshi);
        double w1 = Double.parseDouble(fuwuzhiliang);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
//        System.out.println(username);
        this.userService.updateInfo(h1, s1, f1, w1, username);
        request.setAttribute("f", 1);
        request.getRequestDispatcher(request.getContextPath() + "/updateInfo.jsp").forward(request, response);
    }

    @RequestMapping("/getScore")
    @ResponseBody
    public Map<String, Double> getScore(String username) {
        User user = this.userService.findByUsername(username);
        Map<String, Double> map = new HashMap<>();
        map.put("jiudianweizhi", user.getJiudianweizhi());
        map.put("weishengqingjie", user.getWeishengqingjie());
        map.put("shebeisheshi", user.getShebeisheshi());
        map.put("fuwuzhiliang", user.getFuwuzhiliang());
        return map;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
        request.getRequestDispatcher(request.getContextPath() + "/index.jsp").forward(request, response);
    }

    @RequestMapping("/test")
    public String test1(String username) {
        System.out.println(username);
        return "save";
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(User user) {
        System.out.println(user);
        userService.updateUser(user);
        return "success";
    }

    @RequestMapping("/removeUser")
    @ResponseBody
    public String removeUser(String[] id) {
        int len = id.length;
        for (int i = 0; i < len; i++) {
            userService.removeUser(Integer.parseInt(id[i]));
        }
        return "success";
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user){
        userService.saveUser(user);
        return "success";
    }
}
