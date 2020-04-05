package com.brian.ECPay.Command;

import java.util.ArrayList;
import java.util.Arrays;

public class Commandtest extends ECPayCommand{

	public Commandtest() {
		super("test",new ArrayList<String>(Arrays.asList("ECPay.admin.test")));
	}
	
}
