# Getting started
The app needs a list of codes to function correctly in src/main/resources/codes.txt.
To generate one, use this command: `python src/vote_code_generator.py > src/main/resources/codes.txt`.

For development, there is a sample file with easy to remember codes in src/main/resources/codes.txt.sample.
Simply rename that file.

## Required AppEngine >= 1.9.30
Prior to AppEngine 1.9.30, the local cloud storage needs system admin permissions to write to the disk.
