// IService.aidl
package com.wzy.binderclient;
import com.wzy.binderclient.Person;

parcelable Person;

// Declare any non-default types here with import statements

interface IService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void showToast(String msg);

   void addPerson(in Person person);

}
