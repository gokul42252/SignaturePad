# SignaturePad
Custom view for signature and save signature as jpg image in internal storage

##How to use it in project

###Step 1:Add the JitPack repository to your build file

gradle :

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
maven:
```maven
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
###Step 2: Add the dependency

gradle :
```gradle
dependencies {
	        implementation 'com.github.gokul42252:SignaturePad:1.1.0'
	}
```
maven:
```maven
<dependency>
	    <groupId>com.github.gokul42252</groupId>
	    <artifactId>SignaturePad</artifactId>
	    <version>1.1.0</version>
	</dependency>
```

###Step 3: Add Signature View in Activity.xml
```java
  <com.ct.signaturepad2.SignaturePad
            android:layout_width="300dp"
            android:background="@color/white"
            android:layout_height="200dp"
            android:id="@+id/signaturePad2"
            tools:ignore="MissingConstraints"
            android:layout_marginTop="8dp"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            android:layout_marginLeft="8dp"
            android:layout_marginStart="8dp"
            app:layout_constraintEnd_toEndOf="parent"
            android:layout_marginEnd="8dp"
            android:layout_marginRight="8dp"/>
```
###Step 4: Add Signature View in Activity.java
```java
      super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sign = findViewById<SignaturePad>(R.id.signaturePad2)
        sign.isDrawingCacheEnabled = true
        findViewById<View>(R.id.saveButton).setOnClickListener {
            val img = getBitmapFromView(sign, sign.width, sign.height)
            sign.isDrawingCacheEnabled = false
        }

    }
```
![alt text](http://url/to/img.png)

