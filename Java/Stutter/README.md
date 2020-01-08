# StutterLispLanguage

Stutter is a miniature Lisp-like interpreter.

It parses input into a tree data structure then recursively evaluates it.

The only data type allowed are integers

Each command must be in parenthesis and in prefix notation:
i.e.
(+ 1 2)
would return 3.
(+ 3 4 5 (* 4 5))
would return 37


(def a 3)
The "def" keyword defines "variables" (actually just functions that map to one output)

(* a a)
would return 9
