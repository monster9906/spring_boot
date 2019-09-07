package com.soul.test;

/**
 * @author 符浩灵
 * @date 2019/9/3 18:24
 */
public class Demo1 {
    public static void main(String[] args) {

//        //上面三角形左
//        for (int f=0;f<5;f++){
//            System.out.println();
//            for (int q= 5;q>f;q--){
//                System.out.print(" ");
//            }
//            for (int w= 0;w<(f*2)+1;w++){
//                if(w==0 || w==f*2){
//                    System.out.print("*");
//                }else{
//                        System.out.print("2");
//                }
//
//            }
//
//        }

        count();


    }

    public static void count(){
        double height = 8844.43 *1000; // 毫米为单位
        double zhi = 0.5;
        int count =0;
        for (int i=0;i<height;i++){
            if(zhi <height){
                zhi*=2 ;// 纸的高度翻倍
                count+=1;
            }

            if(zhi >= height){
                break;
            }
        }
        System.out.println("对折的次数："+count);
    }
}
