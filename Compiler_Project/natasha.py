import sys

from natasha_lexer import *

if __name__ == '__main__':
    # file_name = sys.argv[1]
    file_name = "hello.natasha"

    if not file_name.endswith(".natasha"):
        sys.stderr.write('Invalid natasha file')
        sys.exit(1)
        pass
    file = open(file_name)
    characters = file.read()
    file.close()
    tokens = natasha_lex(characters)
    for token in tokens:
        print(token)