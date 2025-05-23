# JPackage GUI
![GitHub release (latest by date)](https://img.shields.io/github/v/release/everdro1d/jpackage-gui?style=flat-square&label=Latest%20Release&logo=github&color=blue)

[Download from GitHub ->](https://github.com/everdro1d/jpackage-gui/releases/latest)

[View Post ->](https://everdro1d.github.io/posts/jpackage-gui/)

---
## Table of Contents

- [JPackage GUI](#jpackage-gui)
- [About](#about)
   - [What is it](#what-is-it)
   - [Why does it exist](#why-does-it-exist)
   - [Planned Features](#planned-features)
- [Usage](#usage)
   - [Creating An Installer](#creating-an-installer)
   - [Locale & Language](#locale--language)
- [Installation](#installation)
   - [Windows](#windows)
   - [MacOS](#macos)
   - [Linux](#linux)
      - [GUI](#gui)
      - [CLI](#cli)
- [Updating](#updating)
   - [Windows & MacOS](#windows--macos)
   - [Linux](#linux-1)
- [Uninstallation](#uninstallation)
   - [Windows](#windows-1)
   - [MacOS](#macos-1)
   - [Linux](#linux-2)

---
## About

### What is it
JPackage GUI is a GUI Application that makes it easier for java developers to package their applications into end-user-friendly installers. It aims to simplify the process of using jpackage, with loads of testing, and jpackage command assembly taken directly from the official documentation, JPackage GUI makes it simple to see what options you need for your use case. Support for Windows, Linux, and MacOS.

### Why does it exist
JPackage GUI came about because I got tired of having to manage and update multiple jpackage scripts for each of my projects, for each OS, and for each version. I wanted a simple way to package my applications without having to worry about whether I was missing any command line arguments (CLI args), and I wanted to be able to do it from a GUI. I also wanted to be able to package my applications for all three platforms (Windows, Linux, and MacOS) while maintaining consistency, and without having to worry about the differences in CLI args between them.

### Planned Features
As of writing this readme, I've still got a few features I want to add:
* ~Monolith File - A single file that saves jpackage settings for all platforms.~ **Done!**
* Version Check - A check if the version has been changed after loading options from a file, before running the installer creation command. I've forgotten to bump the release version too many times... -_-
* Exit Prompt - A dialog that pops up when exiting the program if the options have been changed and not saved before exit.
* Startup Prompt - Remember what option file was loaded in the previous session, and prompt the user on startup if they want to load that file again.
* Option File Repository - A single directory where the user is recomended to save their option files.

---

## Usage

### Creating An Installer
1. Enter the minimum required options for the jpackage program; the input directory, the main jar, the installer type, and the name of the application.
2. Configure any other options in the Generic or OS specific panels.
3. Click the "Create Installer" button.
4. Profit.
5. Extra tip: Save the options as a file for easy future use.

### Locale & Language
In the settings window, you can change the language from a dropdown. This dropdown reads the locale files in the locale directory to populate the list. The locale directory can be opened using the button with a folder icon to the right of the dropdown, and more locales can be found on the github page under the locale directory. This github directory can also be quickly opened using the "Open External" button to the right of the locale directory button.

Locale files use JSON formatting for convenience when translating. When creating translations, rename the file to "locale_[code]", with [code] being the [ISO 639-3](https://en.wikipedia.org/w/index.php?title=ISO_639) three-letter code for the language, and place your file in the locale directory. Re-open the settings window and your locale should appear. If you've done everything correctly and it still isn't showing up, please contact me with the locale information you're trying to add as the validation may have gone askew. I've had to fix and add some locales by hand.

Please note, changing the Display Language currently requires a restart of the application.

---

## Installation

### Windows
1. Download the installer from the Releases section in GitHub.
2. Run the installer. At this step you may encounter a popup from windows defender or an antivirus about the installer being from an unverified developer or that it was downloaded from the internet and may be malicious. This is normal, and good for you.
    - To continue, click on 'Run Anyway' or 'More Info' and then 'Run Anyway', depending on what kind of popup you got. Other antivirus programs tend to have a similar process.
    - After allowing the installer to run normally, you can proceed with the installation steps below.
4. Click next when prompted.
5. After Installation finishes, open the application just like you would any other.

### MacOS
1. Download the installer from the Releases section in GitHub.
2. Run the installer. At this stage you may encounter a popup from Apple stating something along the lines of "from an unidentified developer". This is normal, and good for you [(See Apple's page for more info on this)](https://support.apple.com/en-ca/guide/mac-help/mh40616/mac).
    - When this happens close the dialog and navigate to the installer .pkg file within Finder.
    - Control+Click on the installer file and select 'Open' from the menu.
3. Continue with installation normally. Continue pressing 'next' or 'continue' until finished.
4. After the application has been installed you can find it in Launchpad.

### Linux
#### GUI
1. Download the installer from the Releases section in GitHub.
2. Run the installer. At this stage you should encounter a notification from your package manager stating something along the lines of "from an unidentified developer". This is normal.
    - To proceed with the installation, you may be asked to enter your password in a dialog box. 
3. Continue with installation normally. Continue pressing 'next' or 'continue', until finished.
4. After the application has been installed you can, depending on your distribution, find it in your applications menu or by searching for it in your package manager.

#### CLI
1. Download the installer from the Releases section in GitHub.
2. Open a terminal and navigate to the directory where you downloaded the installer.
3. Run the installer with the following command:
    ```bash
    sudo dpkg -i linux_jpackage-gui_<version>_amd64.deb
    ```
4. If you encounter any dependency issues, run the following command to fix them:
    ```bash
    sudo apt --fix-broken install
    ```
5. If you want to run the application from the terminal, you can do so by typing:
    ```bash
    jpackage-gui
    ```

## Updating
### Windows & MacOS
1. Download the latest installer from the Releases section on GitHub.
2. Run the new installer. It will overwrite the previous version.
### Linux
1. Download the latest .deb installer from the Releases section on GitHub.
2. Remove the old version:
   ```bash
   sudo apt remove jpackage-gui
   ```
3. Install the new version:
   ```bash
   sudo dpkg -i linux_jpackage-gui_<version>_amd64.deb
   ```

## Uninstallation

#### Windows
1. Open the Control Panel and navigate to **Programs and Features**.
2. Find **JPackage GUI** in the list, select it, and click **Uninstall**.

#### MacOS
1. Navigate to the `/Applications` folder.
2. Drag the **JPackage GUI** application to the Trash.
3. Empty the Trash to complete the removal.

#### Linux
1. Open a terminal and run:
   ```bash
   sudo apt remove jpackage-gui
   ```
2. If you want to remove the configuration files as well, run:
   ```bash
    sudo apt purge jpackage-gui
    ```
3. Optionally, you can remove any unused dependencies by running:
   ```bash
   sudo apt autoremove
   ```

