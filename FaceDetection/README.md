# Optimized OpenCV Face Detection
This was a project I worked on while I was in my bachelor program. It's based on the [OpenCV] Library and writen in Java.

[OpenCV]:<http://opencv.org/>

### What does the program do?
So the program is considered to be used on images that have alot of faces. It will detect the faces in the image and generate a new image containing the face for each face existing in the image. The steps are simple as the following:
- Load the image.
- Detect the faces.
- For each face --> make a new image file.

### Optimizing what?
By using the (Theads) consept we where able to decrease the runtime 90% compared to serialized version using a (for loop).

### Development environment:
- Netbeans.

### Usage:
- Install the opencv_1.0.exe file, and make sure to check the extra box(shown bellow), you can find the file in (extras/app):
```
     Add <...>\OpenCV\bin to system PATH during installation
```
- Move the content of the folder (extras\toOpenCv%bin) to (C:\Program Files\OpenCV\bin)
- Move the contents of (\extras\dll) to (c:\windows\system32)
- Reboot your PC
- Make sure you have added the added the library in (extras\lib) to your Java project.

### More:
- Put your input Image in the INPUT folder.
- There is an extra Video Face detection program based on the sample.