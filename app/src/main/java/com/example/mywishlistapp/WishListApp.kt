package com.example.mywishlistapp

import android.app.Application


/* In Android, the Application class is a subclass of the Context class that represents the entire
Android application. It is the base class for all other application components, such as activities,
services, and content providers.The Application class is created before any other application components,
and it remains in memory as long as the application is running. This makes it a good place to initialize
global objects and perform other one-time setup tasks.*/
//Here graph is global object and initialize the data base in mobile is a one time setup task
class WishListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.Provide(this)
    }
}