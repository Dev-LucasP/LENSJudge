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
git clone https://gitlab.univ-artois.fr/lucas_perez/lensjudge.git
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

# 📝 Task distribution

| Feature                                  | Author(s)                       |
|-------------------------------------------------|---------------------------------|
| Test case representation                  | Alexandre                       |
| Problem representation                    | Lucas                           |
| Problem configuration                     | Lucas                           |
| Process representation                   | Nathan et Enzo                  |
| Execution time limit for a process  | Nathan et Enzo                  |
| Memory limit for a process         | Nathan et Enzo                  |
| C program compilation                    | Alexandre                       |
| C++ program compilation                  | Lucas                           |
| Java program compilation                 | Enzo                            |
| Python program compilation               | Nathan                          |
| Execution of a compiled C program              | Lucas                           |
| Execution of a compiled C++ program            | Lucas                           |
| Execution of a compiled Java program           | Lucas                           |
| Strict solution verification             | Lucas                           |
| Verification with tolerance on real numbers       | Nathan                          |
| Verification with tolerance on case sensitivity        | Alexandre                       |
| Verification with tolerance on spaces     | Alexandre                       |
| Verification with tolerance on order         | Enzo                            |
| Verification of one solution among many     | Lucas                           |
| Verification delegated to an external program    | None                         |
| Execution configuration for a test case | Lucas, Nathan, Enzo et Alexandre|
| Main program of the automatic judge          | Lucas, Nathan, Enzo et Alexandre|

# IA usage justification

