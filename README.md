# Passman
This is a password manager for your personal use.

## Description

This application is built using Scene Builder,
Java, and Intellij. It was built as a part of my own interests in having a safe program
where I was the author.  I don't really trust large companies to store my personal
information.  Thus, I made a solution for myself.  Using this app in combination with my password 
generator app will keep your online profile secure.  The application uses AES encryption to 
secure your information.  I left the choice up to the user when to encrypt/decrypt info and 
where to load and save the information on your computer.

## Getting Started

![splash screen](https://github.com/tiptonspiderj/Passman/blob/main/src/images/Logo.JPG)

### Dependencies

The dependencies are JavaFX v17 and the external jars included in the "lib" folder

The runnable jar file is located in the "input" folder

### Installing for Windows

You can make an executable installer from my project using java's jpackage tool and the following commands:
```
 jlink --module-path "Your path to JavaFX-Mods-jars" --add-modules=ALL-MODULE-PATH --output runtime
 
jpackage -t exe --name Passman --description "Password manager Author: Jeremy Tipton" --app-version 1.0.0 --input input 
--dest output --main-jar Passman.jar --win-shortcut --runtime-image runtime --icon src\images\Passman1.ico
```
### Executing program

If you just want to run the program from the executable jar in the "input" folder, you can use the command:
```
java -jar --module-path "YOUR PATH TO\javafxsdk17.0.0.1\lib" --add-modules=ALL-MODULE-PATH Passman.jar
```
## Authors

Contributor’s names and contact info

Jeremy Tipton  
[@tiptonspiderj1](https://tiptonspiderj1.com)

## Version History

* 1.0.0
    * Initial Release

## Feedback

If you have any feedback, please reach out to me at tiptonspiderj1@aol.com

## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Windows Terminal](https://img.shields.io/badge/Windows%20Terminal-%234D4D4D.svg?style=for-the-badge&logo=windows-terminal&logoColor=white)

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)

