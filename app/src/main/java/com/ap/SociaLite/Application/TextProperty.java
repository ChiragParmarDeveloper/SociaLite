package com.ap.SociaLite.Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TextProperty {

    private int heigt;                              //读入文本的行数
    private String []context = new String[1024];    //存储读入的文本

    /*
     *@parameter wordNum
     *
     */
    public TextProperty(int wordNum , InputStreamReader in) throws Exception {
        int i=0;
        BufferedReader br = new BufferedReader(in);
        String s;
        while((s=br.readLine())!=null){
            if(s.length()>wordNum){
                int k=0;
                while(k+wordNum<=s.length()){
                    context[i++] = s.substring(k, k+wordNum);
                    k=k+wordNum;
                }
                context[i++] = s.substring(k,s.length());
            }
            else{
                context[i++]=s;
            }
        }
        this.heigt = i;
        in.close();
        br.close();
    }


    public int getHeigt() {
        return heigt;
    }

    public String[] getContext() {

        return context;
    }
}


