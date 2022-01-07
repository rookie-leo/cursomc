package com.leonardo.cursomc.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Long> decodeLongList(String str) {
		String[] vet = str.split(",");
		List<Long> list = new ArrayList<>();
		for (int i = 0; i < vet.length; i++) {
			list.add(Long.parseLong(vet[i]));
		}

		return list;
	}

}
