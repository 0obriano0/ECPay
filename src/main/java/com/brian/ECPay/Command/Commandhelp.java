package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;

public class Commandhelp extends ECPayCommand{

	public Commandhelp() {
		super(  "help",
				"",
				new ArrayList<String>(Arrays.asList("ECPay.user.help")));
	}

}
