package br.com.smu.transformer;

import com.google.gson.GsonBuilder;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

	@Override
	public String render(Object obj) throws Exception {
		return new GsonBuilder().setPrettyPrinting().create().toJson(obj);
	}

}

