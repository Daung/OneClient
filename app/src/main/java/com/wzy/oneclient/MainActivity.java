package com.wzy.oneclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;

import com.wzy.binderclient.IService;
import com.wzy.binderclient.Person;


//这个是客户端，按照固定的协议连接服务器，就可以向服务器发送消息了

public class MainActivity extends AppCompatActivity {


    private IService mService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IService.Stub.asInterface(service);
            showToast("连接成功");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            showToast("连接失败");
        }
    };
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent =new Intent("android.intent.action.service");
        intent.setPackage("com.wzy.binderclient");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    public void passValue(View view) {
        try {
            mService.showToast("这是客户端传过去的字符串");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void passPersonValue(View view) {
        Person person = new Person();
        person.setName("王五");
        person.setAge(5);
        try {
            mService.addPerson(person);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
