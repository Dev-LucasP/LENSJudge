#!/bin/bash

# Ask the user if they use bash or zsh
read -p "Do you use bash or zsh? (1 = bash, 2 = zsh): " shell_choice

# Create the ~/bin directory if it doesn't exist
[ -d ~/bin ] || mkdir ~/bin

echo "Building .jar file ..."

# Building .jar (app/build/libs/app.jar)
./gradlew clean build 

cp ./app/build/libs/app.jar ~/bin/
echo 'java -jar ~/bin/app.jar "$@"' > ~/bin/lensjudge
chmod +x ~/bin/lensjudge

# Add the custom bin to the appropriate shell configuration file
if [ "$shell_choice" = "1" ]; then
    if ! grep -q 'export PATH=$PATH:~/bin' ~/.bashrc; then
        echo 'export PATH=$PATH:~/bin' >> ~/.bashrc
        source ~/.bashrc
    else
        echo "Path already added to .bashrc"
    fi
elif [ "$shell_choice" = "2" ]; then
    if ! grep -q 'export PATH=$PATH:~/bin' ~/.zshrc; then
        echo 'export PATH=$PATH:~/bin' >> ~/.zshrc
        source ~/.zshrc
    else
        echo "Path already added to .zshrc"
    fi
else
    echo "Invalid choice. Please run the script again and choose either '1' or '2'."
    exit 1
fi
