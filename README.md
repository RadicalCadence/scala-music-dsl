[![Build Status](https://travis-ci.org/RadicalCadence/scala-music-dsl.svg)](https://travis-ci.org/RadicalCadence/scala-music-dsl)

Describing Music in Scala with Domain Specific Languages
========================================================

In our humble experience, there is no easy or scalable way to write and typeset
music on the computer. Professional tools are too expensive and feature-heavy, most
open source projects are not user friendly, and neither solution offers
functional abstractions or room for extensions.

This repository is an experiment to create an expressive music tool that fills
the gap that projects like Abjad and Lilypond have left open. Ideally, we aim
to create a Scala library that offers a simple yet powerful DSL to succinctly and
easily write music, which can be parsed by Scala to be manipulated, typeset, and
performed live. 

Potential users should be highly wary of using this library until the syntax
is completely nailed down. 

Requirements
------------

* [Scala](http://www.scala-lang.org/)
* [SBT](http://www.scala-sbt.org/)
* [LilyPond](http://www.lilypond.org/index.html)

Getting Started
---------------

Here is a quick example you can do to make sure you have your environment 
set up correctly.

1. Make sure you have Scala, SBT, and LilyPond installed. 
2. Clone this repositiory.
3. Open a terminal and navigate to the root directory of this repo. 
4. Start SBT and open a REPL by typing `sbt console`
5. Type `DSLParser("| B4 C4 D4 E4 |")` You can also use `m"| B4 C4 D4 E4|"` as a shortcut.
6. You should get back a music object.
7. Type `ly"| B4 C4 D4 E4 |"`
8. You should get back a String that is in LilyPond format. 
9. You can also create the Lilypond file directly.
10. Type `show"| B4 C4 D4 E4 |"`
11. Assuming your LilyPond is set up correctly, you should now have a .ly file 
generated in the current directory and a corresponding pdf. 
12. If you can see the pdf then you are good to go! Time to start exploring.

Syntax
------

The project uses two major StringContext shortcuts.
They are `m`, which is a StringContext shortcut to create music objects in the DSL format, and `show`, 
which is a StringContext shortcut to create a LilyPond file and its corresponding PDF. 

For example, `m"| C4 C4 C4 C4|"` would make a DSL music object. 

This section will introduce syntax for most of the 
currently implemented features.

Note
~~~~

You can create a note by entering the letter followed by the duration. 

A C quarternote: `C4`
A D halfnote:    `D2`

Pitch
~~~~~

Notes can also have pitches. Here's some examples with C.

Blank: `C`
Natural: `Cn`
Sharp: `C#`
Flat: `C-`
DoubleSharp: `Cx` or `C##`
DoubleFlat: `C_`

The pitch goes after the note and before the duration. For example, a C sharp quarternote: `C#4`.

Octaves
~~~~~~~

You can go up an octave by typing `'`, or go up more octaves by typing `''` etc. 
You can down an octave by typing ',' or go down more octaves by typing `,,` etc.

The octave goes after the note (and pitch if there is one) and before the beat: `C#''4`.

Measure
~~~~~~~

Measures consist of Notes (with pitches, beats, octaves etc) between two `|`.
For example, `| C#4 D#4 E4 F4 |`.

Time Measure
~~~~~~~~~~~~

Optionally, within a measure you can denote the time
measure with `[a/b]`: `| [3/4] C4 D4 E4 |`.

Staff
~~~~~

A staff consists of one or more measures. For example,
`| C4 C4 C4 C4 | D4 D4 D4 D4 |`

Contributing
------------

This project is Open Source and is released under the MIT license. We 
welcome any contributions, whether they be bug fixes, new features, or 
documentation. 

If you want to report a bug, or suggest a feature, feel free to open an 
issue in the project's issue tracker.

If you want to submit a patch, simply issue a pull request and we will get 
in touch.

Authors
-------

* Griffin Moe (gmoe@luc.edu)
* George K. Thiruvathukal (gkt@cs.luc.edu)
* Brian Gathright (bgathright@luc.edu)
