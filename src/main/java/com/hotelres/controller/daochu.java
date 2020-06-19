package com.hotelres.controller;

import com.hotelres.bean.Hotel;
import com.hotelres.service.impl.HotelServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class daochu {

    public static void main(String[] args) {
        try {
            File file = new File("p.data");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getName(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            for (int i = 1; i < 119; ++i) {
//                for (int j = 1; j < 5; ++j) {
//                    double a = Math.random()*5;
//                    DecimalFormat df = new DecimalFormat( "0.0" );
//                    String str=df.format( a );
//                    String data = i+","+j+","+str+"\r\n";
//                    bufferedWriter.write(data);
//                }
//            }
            HotelServiceImpl hotelService = new HotelServiceImpl();
            List<Hotel> list = hotelService.findAll("");
            System.out.println(list.size());
            for (Hotel hotel : list){
                String data1 = hotel.getHid()+","+1+","+hotel.getJiudianweizhi()+"\r\n";
                bufferedWriter.write(data1);
                String data2 = hotel.getHid()+","+2+","+hotel.getWeishengqingjie()+"\r\n";
                bufferedWriter.write(data2);
                String data3 = hotel.getHid()+","+3+","+hotel.getShebeisheshi()+"\r\n";
                bufferedWriter.write(data3);
                String data4 = hotel.getHid()+","+4+","+hotel.getFuwuzhiliang()+"\r\n";
                bufferedWriter.write(data4);
            }
            bufferedWriter.close();



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
