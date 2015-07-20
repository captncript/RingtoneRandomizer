package com.captncript.RingtoneRandomiser;

import android.app.*;
import android.os.*;
import android.app.AlertDialog;

import java.io.*;
import android.content.*;
import android.view.*;
import android.media.*;
import android.database.*;
import android.net.*;
import android.widget.*;

public class MainActivity extends Activity 
{
	public static final String BROADCAST = "android.provider.Telephony.SMS_RECEIVED";
	public static final String PERMS = "android.permission.RECEIVE_SMS";
	
	BroadcastReceiver br = new captnReceiver();
	//The filtet tells the receiver what to look for
	IntentFilter intentfilter = new IntentFilter(BROADCAST);
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//This is here for error reporting
		try
		{
			File file = new File("/storage/emulated/0/AppProjects/RingtoneRandomiser/error");
			PrintStream ps = new PrintStream(file);
			System.setOut(ps);
		} catch (IOException e)
		{
			System.out.println("Error setting up reporting");
		}
		//This is the end of error setup
		System.out.println("started");
	}
	
	public void setBr(View view) {
		//This is called by a button in main.xml and sets the receiver that sees incoming texts
		try {
			System.out.println("attempting to register a receiver");
			registerReceiver(br, intentfilter);
		} catch (Error e) {
			System.out.println(e);
		}
		System.out.println("Registered");
	}
	
	public void unSetbr(View view) {
		//This removes the receiver
		unregisterReceiver(br);
		System.out.println("Unregistered");
	}
	
	public void sendingBroadcast(View view) {
		//this is now for testing ringtone setting
		
		RingtoneManager rm = new RingtoneManager(getApplicationContext());
		rm.setType(RingtoneManager.TYPE_NOTIFICATION);
		Cursor ringtones = rm.getCursor();
		Uri nURI = Uri.EMPTY;
		//Uri oURI = rm.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION);
		TextView tv = (TextView)findViewById(R.id.id1);
		int numOfRingtones = ringtones.getCount(); //use this for a random picker
		
		tv.setText(rm.getRingtoneUri(0).toString());
		nURI.equals(rm.getRingtoneUri(0));
		rm.setActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION,nURI);
		System.out.println("New ringtone: " + rm.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION).toString());
		//rm.setActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION,oURI);
		
		//System.out.println("The uri variable: " + rm.getRingtoneUri(0));
		//System.out.println(ringtones.getString(RingtoneManager.URI_COLUMN_INDEX));
		//System.out.println(ringtones.getString(1));
		//System.out.println(ringtones.getString(2));
		//System.out.println(ringtones.getString(3));
		//System.out.println("Number of coloumns: " + ringtones.getColumnCount());
		//System.out.println("Number of rows: " + ringtones.getCount());
		System.out.println("Current notification ringtone: " + RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION)); //This reports the URI of the new ringtone
		
	}
	//End of class
}

