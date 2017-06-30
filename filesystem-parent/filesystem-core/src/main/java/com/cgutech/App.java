package com.cgutech;

import java.util.*;
public class App{
	public static void main(String[] args){
		PrintVampire();
		//System.out.println("123".contains("155"));
	}

	public static void PrintVampire(){
		for (int i=1000;i<=9999 ;i++ ) {
			if (Judge(i)) {
				//System.out.println(i);
			}
		}
		
	}
	public static boolean Judge(int hh){
		String[] st = (""+hh).split("");
		HashSet<String> ls= new HashSet<>();
		for (String var:st) {
			for (String ber:st) {
				ls.add(var+ber);
			}
		}
		for (String var:ls) {
			for (String ber:ls) {
				int xb= (int)Integer.parseInt(var)*Integer.parseInt(ber);
				if(xb==hh&&containsAll(st,ber+var)){
					System.out.println(hh+"="+var+"x"+ber);
					return true;
				}
			}
		}
		return false;
	}
	public static boolean containsAll(String[] con,String bon){
		for (String s:con ) {
			if(!bon.contains(s)){
				return false;
			}
		}
		return true;
		
	}
}