package com.epsilon.training.annotations.processors;

import java.util.List;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.epsilon.training.annotaions.XMLIgnored;

import com.epsilon.training.annotations.XMLProperty;
import com.epsilon.training.annotations.XMLSerializable;
import com.epsilon.training.exceptions.NotJsonSerializableException;

public class XMLSerializer {
	public void serialize(Writer writer, Object obj) {
		String json = this.serialize(obj,0);
		try(PrintWriter out = new PrintWriter(writer);){
			out.print(json);
		}
	}
		
	public String serialize(Object obj,int sp) {

		sp++;
		
		if (obj == null) {
			throw new NotJsonSerializableException("Cannot serialize a null object!");
		}

		// cls represents the class, obj is an object of
		Class<?> cls = obj.getClass();
		//System.out.println(obj.getClass().getSimpleName());


		if (!cls.isAnnotationPresent(XMLSerializable.class)) {
			throw new NotJsonSerializableException("The class " + cls.getName() + " does not have @JsonSerializable");
		}

		List<String> kvList = new ArrayList<>();
		for (Field f : cls.getDeclaredFields()) {
			if (!f.isAnnotationPresent(XMLIgnored.class)) { //to check they are ignored
				try {
					
					String label = f.getName();
				
					
					XMLProperty ann = f.getAnnotation(XMLProperty.class);
					
					if(ann!=null) { //to check if are passing any other name
						label = ann.label();
						if(label ==null || label.trim().equals("")) {
							label = f.getName();
						}
					}
					
					
					f.setAccessible(true);
					 
					Class<?> classofobjaddr = f.get(obj).getClass();
					if(classofobjaddr.isAnnotationPresent(XMLSerializable.class))
					{
						//Recursion 
						XMLSerializer j = new XMLSerializer();
						String val =j.serialize(f.get(obj),sp);
						kvList.add( val);
					}
					else
					{
						String a="";
						for(int j=1;j<=sp;j++)
						{ a=a+ "   ";
						}
					String kv = a+String.format("<%s>%s</%s>", label, f.get(obj),label);
					
					
					//System.out.println(kv); //"fname" :"santhra"
					kvList.add(kv);
					}
				} catch (Exception e) {
					System.err.println("Erorr for field " + f.getName() + " - " + e.getMessage());
				}
			}
		}

		StringBuffer sb = new StringBuffer(1000);
		String hp="";
		if(sp>1)
		{
			for(int l=1;l<=sp-1;l++)
			{
				hp=hp+"   ";
			}
		}
		String begin=hp+"<"+obj.getClass().getSimpleName()+"> \n";
		sb.append(begin);
		for (int i = 0, j = kvList.size() - 1; i < j; i++) {
			sb.append(kvList.get(i));
			sb.append("\n");
			
		}
		sb.append(kvList.get(kvList.size() - 1));
		String end="\n"+hp+"</"+obj.getClass().getSimpleName()+">";
		sb.append(end);
	
		return sb.toString();
	}
}
