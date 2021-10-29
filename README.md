# Passman
This is a password manager for your personal use.

## Description

This project was made using the Eclipse IDE along with the e(fx)clipse plugin. I also used Scenebuilder to create the *.fxml files in the project.
The application uses AES encryption to secure your information.  I left the choice upto the user when to encryp/decrypt info and where to load and
save the information on your computer.  There are still a few logic things I need to fix to prevent user error.  Those will be finished shortly.

## Getting Started

### Dependencies

The dependencies are JavaFX v17 and the external jars included in the "lib" folder

The runnable jar file is located in the "input" folder

### Installing for Windows

You can make an executable installer from my project using java's jpackage tool and the following commands:

 jlink --module-path "Your path to JavaFX-Mods-jars" --add-modules=ALL-MODULE-PATH --output runtime
 
jpackage -t exe --name Passman --description "Password manager Author: Jeremy Tipton" --app-version 1.0.0 --input input 
--dest output --main-jar Passman.jar --win-shortcut --runtime-image runtime --icon src\images\Passman.ico

### Executing program

If you just want to run the program from the executable jar in the "input" folder you can use the command:

java -jar --module-path "YOUR PATH TO\javafxsdk17.0.0.1\lib" --add-modules=ALL-MODULE-PATH

## Help

## Authors

Contributors names and contact info

Jeremy Tipton  
[@tiptonspiderj1](https://tiptonspiderj1.com)

## Version History


* 1.0.0
    * Initial Release

## License

This project is licensed under the [NAME HERE] License - see the LICENSE.md file for details

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)

