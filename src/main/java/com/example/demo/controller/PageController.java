package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}
	
	//@RequestMapping("/hello2")
	//public String hello2(@RequestParam(value = "name", required = false, defaultValue = "Thanos") String name, Model model) {
		//model.addAttribute("name", name);
		//return "hello2";
	//}
	
	@RequestMapping(value= {"/hello2","hello2/{name}"})
	public String helloPath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}
		else {
			model.addAttribute("name", "Phoenix");
		}
		return "hello2";
	}
	
	@RequestMapping("/calculator")
	public String calculate(@RequestParam(value = "number1", required=false, defaultValue = "0") String number1, @RequestParam(value = "number2", required=false, defaultValue = "0") String number2,
			Model model) {
		
		model.addAttribute("number1", number1);
		model.addAttribute("number2", number2);
		
		String[] satuan = { "", " satu", " dua", " tiga", " empat", " lima", " enam", " tujuh", " delapan", " sembilan",
				" sepuluh" };
		String[] puluhan = { "", " belas", " dua puluh", " tiga puluh", " empat puluh", " lima puluh", " enam puluh",
				" tujuh puluh", " delapan puluh", " sembilan puluh" };
		String[] ratusan = { "", " seratus", " dua ratus", " tiga ratus", " empat ratus", " lima ratus", " enam ratus",
				" tujuh ratus", " delapan ratus", " sembilan ratus" };
		String[] ribuan = { "", " seribu", " dua ribu", " tiga ribu", " empat ribu", " lima ribu", " enam ribu",
				" tujuh ribu", " delapan ribu", " sembilan ribu" };

		int no1 = Integer.parseInt(number1);
		int no2 = Integer.parseInt(number2);

		int jumlah = no1 + no2;
		if (jumlah < 9999 && jumlah > 0) {
			int a, b, c, d;
			a = jumlah / 1000;
			b = (jumlah % 1000) / 100;
			c = (jumlah % 100) / 10;
			d = jumlah % 10;
			if (c == 1) {
				if (d == 1) {
					model.addAttribute("kata", ribuan[a] + ratusan[b] + "se" + puluhan[c]);
				} else {
					model.addAttribute("kata", ribuan[a] + ratusan[b] + satuan[d] + puluhan[c] );
				}
			} else {
				model.addAttribute("kata", ribuan[a] + ratusan[b] + puluhan[c] + satuan[d]);
			}
		} else {
			model.addAttribute("kata", "Masukan Input Antara 0 - 9999");
		}

		String hasil = Integer.toString(jumlah);
		
		model.addAttribute("hasil", hasil);
		
		return "calculator";
	}
}
