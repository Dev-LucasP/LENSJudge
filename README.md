# 📦 Installation and Usage 

## 📌 Dependencies

### You need to have the following dependencies installed:
- 📦 `g++` to compile C++ testfiles
- 📦 `gcc` to compile C testfiles
- 🐍 `python3` to run python testfiles
- ☕ `javac` to compile Java testfiles
- ☕ `java` at least `version 21` to compile the testing framework
## 🛠️ Installation

```bash
git clone https://github.com/Dev-LucasP/LENSJudge.git
chmod +x install.sh
./install.sh
```

## 🚀 Usage

```bash
$ lensjudge
lensjudge <sourceFile> <test.in> <test.ans>  # For a single test
lensjudge <sourceFile> <test-dir>            # For a directory of tests
```

## 📚 Example

```bash
$ lensjudge main.cc test.in test.ans
# Check if the output of main.cc is correct for test.in
```
```bash
$ lensjudge main.cc test-dir
# Check if the output of main.cc is correct for all tests in test-dir
```

## 📝 Note

- The installation script will install the `lensjudge` command in `~/bin/` since the IUT computers does not have sudo right to copy a file into `/usr/local/bin`.

# 📊 Diagrams of the project
![plantuml_graph](plantuml/lensjudge.png)
- You can check the plantuml code in the `plantuml/lensjudge.plantuml` directory
