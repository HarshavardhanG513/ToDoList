package com.harsha.todolist.Security;

import java.util.*;
class Encryption
{
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args)
    {
        String s="";
        int choice=1;
        int count=0;
        while(choice==1)
        {
            System.out.println("enter string");
            if(count!=0)
                sc.nextLine();
            s=sc.nextLine();
            System.out.println("1 to encrypt, 2 to decrypt");
            choice=sc.nextInt();
            if(choice==1)
                encrypt(s);
            else if(choice==2)
                decrypt(s);
            System.out.println("1 to continue, anything else to exit");
            choice=sc.nextInt();
            count++;
        }
    }

    public static void encrypt(String s)
    {
        int size=s.length()%6==0?s.length()/6:s.length()/6+1;
    	char[][] array=new char[6][size];
        int index=0;
        for(int i=0;i<array.length;i++)
        {
            for(int j=0;j<array[0].length;j++)
            {
                if(index<s.length())
                    if(s.charAt(index)==' ')
                        array[i][j]='*';
                    else
                        array[i][j]=s.charAt(index);
                else
                    array[i][j]='.';
                index++;
            }
        }
        String encrypted="";
        for(int i=0;i<array[0].length;i++)
        {
            for(int j=0;j<array.length;j++)
            {
                encrypted=encrypted.concat(Character.toString(array[j][i]));
            }
        }
        System.out.println(encrypted);
    }

    public static void decrypt(String s)
    {
        char[][] array=new char[(int)(s.length()/6)][6];
        int index=0;
        for(int i=0;i<array.length;i++)
        {
            for(int j=0;j<array[0].length;j++)
            {
                if((s.charAt(index)=='*')||(s.charAt(index)=='.'))
                    array[i][j]=' ';
                else
                    array[i][j]=s.charAt(index);
                index++;
            }
        }
        String decrypted="";
        for(int i=0;i<array[0].length;i++)
        {
            for(int j=0;j<array.length;j++)
            {
                decrypted=decrypted.concat(Character.toString(array[j][i]));
            }
        }
        System.out.println(decrypted);
    }
}