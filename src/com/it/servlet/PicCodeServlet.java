package com.it.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/code")
public class PicCodeServlet extends HttpServlet {
    private Random ran = new Random();

    //1) 写一个方法随机获取颜色
    private Color getColor() {
        int r = ran.nextInt(256);
        int g = ran.nextInt(256);
        int b = ran.nextInt(256);
        return new Color(r, g, b);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2) 创建缓存图片：指定宽width=90，高height=30
        int width = 90, height = 30;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //3) 获取画笔对象
        Graphics graphics = img.getGraphics();
        //4) 设置画笔颜色
        graphics.setColor(Color.WHITE);
        //并且填充矩形区域
        graphics.fillRect(0, 0, width, height);
        //5) 从字符数组中随机得到字符
        char[] arr = {'A', 'B', 'C', 'D', 'N', 'E', 'W', 'b', 'o', 'y', '1', '2', '3', '4'};
        //6) 设置字体，大小为18
        //1. 字体 2. 样式：加粗+斜体 3. 大小
        graphics.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,19));
        //创建一个会话对象
        HttpSession session = request.getSession();

        //保存验证码
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            //画4个字，设置字的颜色随机
            graphics.setColor(getColor());
            //7) 将每个字符画到图片，位置：5+(i*20), 20
            //得到下标
            int index = ran.nextInt(arr.length);
            //得到字符
            char c = arr[index];
            sb.append(c);
            //把字符转成字符串
            graphics.drawString(String.valueOf(c),5+(i*20), 20);
        }
        //将验证码放在会话域中，转成字符串
        session.setAttribute("vcode", sb.toString());
        System.out.println(sb);
        //8) 画干扰线8条线，线的位置是随机的，x范围在width之中，y的范围在height之中。
        for (int i = 0; i < 8; i++) {
            graphics.setColor(getColor());
            int x1 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int x2 = ran.nextInt(width);
            int y2 = ran.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);
        }
        //9) 将缓存的图片输出到响应输出流中
        //1. 要写到浏览器上图片  2. 图片格式 3. 输出到哪个流中
        ImageIO.write(img, "jpg", response.getOutputStream());
    }
}

