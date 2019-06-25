# MinimalGraph

## Implementation
Before you can use this API, we have to implement it of course. For this you should open the `build.gradle`-File under your `app`-Folder inside your project and add the following line:
```
...
dependencies {
  ...
  implementation 'com.github.ParzivalExe:MinimalGraph:0.1.0'
  ...
}
```
After this you also have to add the following line into your `build.gradle`(This time under your Project-Folder itself): 
```
...
allprojects {
  repositories {
    ...
    maven {url 'https://jipack.io'}
  }
}
```
As you saw, it isn't much different to the implementation of any other API in Android.

## Usage
Now I will show you very fast, how to use this API. As I said, it isn't really that big of a deal, since this API is all about minimal design and minimal work.

First you have to add the View to your layout.xml-File:
```XML
<com.bluebird.apps.grouporg.api.views.GraphView
                    android:id="@+id/testGraph"
                    android:layout_width="match_parent"
                    android:layout_centerInParent="true"
                    android:layout_height="80dp"
                    android:layout_marginLeft="5dp"
                    android:layout_marginRight="5dp"
                    android:layout_marginBottom="12dp"
                    app:borderColor="@color/colorBorder"
                    app:graphColor="?attr/colorAccent"/>
```
This is of course just an example and you can change everything the way you want. The two new Arguments are `app:borderColor` and `app:graphColor` in this View, which do exacly what you would expect them to do, setting the color for the border of your Graph (also known as X and Y-Axis 😉) and the color of your graph.

But we aren't done yet, because we have to add some values. Otherwise there won't be a graph. This unfortunatly is only possible with Java-Code right now, because XML can't handle int-Arrays right now.

So, let's look at this small part of Java:
```Java
GraphView testGraph = (GraphView) findViewById(R.id.testGraph);
testGraph.addActivePoint(35);
testGraph.addActivePoint(40);
testGraph.addActivePoint(30);
testGraph.addActivePoint(25);
testGraph.addActivePoint(40);
```
**AND DONE!** As you can see, you really can create a graph in less than a minute with this API. Of course it is very simple, but this isn't anything you would use in science-work anyway.
