# Installation and Usage

## Installation

```bash
chmod +x install.sh
./install.sh

## Usage

```bash
$ lensjudge
lensjudge <sourceFile> <test.in> <test.ans>  # For a single test
lensjudge <sourceFile> <test-dir>            # For a directory of tests

## Example

```bash
$ lensjudge main.cpp test.in test.ans
# Check if the output of main.cpp is correct for test.in
```bash
$ lensjudge main.cpp test-dir
# Check if the output of main.cpp is correct for all tests in test-dir

## Note

- The installation script will install the `lensjudge` command in `~/bin/` since the IUT computers does not have sudo right.