# Diffinder
A utility that compares two files and returns how they differ. Analog of the "Diff" command in Linux, with simpler output. 

**Usage:**
1. Download [.jar file](https://drive.google.com/file/d/1CrvZbGxFTcRNp0Ynw2NBDyqz4IJbq4qI/view)
2. Download [script](https://drive.google.com/file/d/1J9fFmnUSMMTiC72MAJOXr9W_TsgWiZC4) (must be located in the same directory)
3. Add the path to the directory where Diffinder.jar and diffinder.sh are installed to the PATH variable
   ```export PATH=$HOME/downloads```

**Example:**
```bash
# ./diffinder.sh ~/815.txt ~/817.txt

 VERSION DIFFERENCES:
    815.txt - solvo-devel-0.2.15-46.el8.x86_64
    817.txt - solvo-devel-0.2.15-45.el8.x86_64

    815.txt - solvo-yum-1.0-5.noarch
    817.txt - solvo-yum-1.0-4.noarch

    815.txt - solvo-gateway-odbc-2.12.10-13.el8.x86_64
    817.txt - solvo-gateway-odbc-2.12.10-11.el8.x86_64

    815.txt - solvo-xml-0.2.15-46.el8.x86_64
    817.txt - solvo-xml-0.2.15-45.el8.x86_64

    815.txt - solvo-gateway-2.12.10-13.el8.x86_64
    817.txt - solvo-gateway-2.12.10-11.el8.x86_64

    815.txt - solvo-bin-0.2.15-46.el8.x86_64
    817.txt - solvo-bin-0.2.15-45.el8.x86_64

    815.txt - solvo-libs-0.2.15-46.el8.x86_64
    817.txt - solvo-libs-0.2.15-45.el8.x86_64

 817.txt CONTAINS THAT AREN'T IN 815.txt:
    solvo-dms-1.0-1.noarch
    solvo-bs-1.0-1.noarch
    solvo-common-1.0-7.noarch
    solvo-ctms-1.0-2.noarch
    solvo-binary-1.0-1.noarch
    solvo-maven-1.1-5.noarch
    solvo-kit17-1.0-1.noarch
    solvo-dist-1.0-7.noarch
    solvo-kit20-1.0-1.noarch
    solvo-kit21-1.0-1.noarch
    solvo-kit16-1.0-1.noarch
```
