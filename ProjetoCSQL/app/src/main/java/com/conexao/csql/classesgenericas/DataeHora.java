package com.conexao.csql.classesgenericas;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataeHora{
    public String data(){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    } public String diames(){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String hora(){
        
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String horaeminuto(){
        
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }    
   public String horaeminuto1(){
        
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime(date.getTime() + (100 * 60 * 60 * 10));  
        return dateFormat.format(date);
    }   
   public String getDateTime(Date d) {
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      
       return dateFormat.format(d);
   }
   
   public String formata10(Date d){
       
   return getDateTime(d);
   }
  public String getMesHoraM
  () {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      Date date = new Date();
      //return dateFormat.format(date).substring(0,dateFormat.format(date).length()-1)+"0";
      return dateFormat.format(date);
  } 

public String somaDias(String data, int dias){
	
	int ano= Integer.parseInt(data.split("/")[2]);
	int mes= Integer.parseInt(data.split("/")[1]);
	int dia= Integer.parseInt(data.split("/")[0]);

    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    
 /*   Calendar c = new GregorianCalendar(ano, mes, dia);   
     c.add(Calendar.DAY_OF_MONTH, dias); */  
    Date a = new Date(ano+"/"+mes+"/"+dia);
    a.setDate(a.getDate() + dias);   
   return sd.format(a.getTime());  
     
	
}

 
	public String somaMeses(String data, int meses){
		
		int ano= Integer.parseInt(data.split("/")[2]);
		int mes= Integer.parseInt(data.split("/")[1]);
		int dia= Integer.parseInt(data.split("/")[0]);

	    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	    
	    Calendar c = new GregorianCalendar(ano, mes, dia);
	      
	       
	    c.add(Calendar.MONTH, meses);
	    
	   return sd.format(c.getTime());  
	     
	}
 
	public String somaAno(String data, int anos){
		
		int ano= Integer.parseInt(data.split("/")[2]);
		int mes= Integer.parseInt(data.split("/")[1]);
		int dia= Integer.parseInt(data.split("/")[0]);

	    SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	    
	    Calendar c = new GregorianCalendar(ano, mes, dia);
	      
	       
	    c.add(Calendar.YEAR, anos);
	    
	   return sd.format(c.getTime());  
	     
	}




    public String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);

    }
}