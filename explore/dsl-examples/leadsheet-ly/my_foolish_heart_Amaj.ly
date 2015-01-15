% "My Foolish Heart" by Washington/Young (Evans)
% A major, slowly and expressively

\version "2.18.2"
\header {
  title = "My Foolish Heart"
  composer = "Washington/Young (Evans)"
}

chExceptionMusic = {
  <c ees ges bes>1-\markup { \raise #1.0 { \small { \concat { "-7" \smaller {\flat} "5"} } } }
}

chExceptions = #(append
    (sequential-music-to-chord-exceptions chExceptionMusic #t)
    ignatzekExceptions) 

<<
  \chords { 
    \set chordNameExceptions = #chExceptions 
    \set majorSevenSymbol = \markup { maj7 }
    \set minorChordModifier = \markup { "-" }
    \set noChordSymbol = \markup { }

    r4 | 
    a2:maj7 e:maj7 | cis:m7 fis:7 |
    b:m7 b:m9/a | a:7sus4 a:7 |
    cis:m7 cis:7.9+ | fis:m7 d:7 |
    \mark \markup { \musicglyph #"scripts.coda" }
    b1:m7 | b2:m7.5- e:7.9- |
    a1:maj7 | fis2:m9 a:aug7 |
    d1:maj7 | gis2:m7.5- cis:7 |
    fis:m7 cis:7.9+ | fis:m7 b:7 |
    b:m7 fis:aug7 | b:m7 e:7 |
    \repeat unfold 3 {
      s1
      \bar ""
    }
    \mark \markup { \musicglyph #"scripts.coda" }
    b2:m7 b2:m7/a | gis:m7.5- cis:7 |
    fis1:m7 | d2:m7 g:7 | a:maj7 d:maj7 |
    g:7 fis:7 | b:m7 fis:7 | b4:13 b:aug7 e:9sus4 e:7.9- |
    a2:maj7 fis:m7 | f:maj7 e:7sus4
  }

  \relative c' {
    \key a \major
    \time 4/4

    \partial 4 e4 | \bar".|:"
    %\override Score.KeySignature #'stencil = ##f
    %\override Score.Clef #'stencil = ##f 
    e2~ e8 a,8 cis8 e8 | fis8 gis8 fis2 fis4 | 
    fis2~ fis8 b, d fis | a2. a4 |
    a2~ a8 cis, e gis | a b a2 a4 | \bar"||"
    a2~ a8 b, d fis | b2. a8 b | \bar"||"
    cis4. cis8 cis4 b8 a | cis4 cis2 b8 a |
    b4. b16 b \times 2/3 { b8 b a } fis8 gis |
    b2. a8 gis | a4. a8 a gis fis gis |
    a4 a2 gis8 fis | gis4. gis8 gis e fis e |
    gis2. fis4 | \bar":|."
    \cadenzaOn
      \stopStaff
        % text and symbols center-aligned
        \repeat unfold 1 {
          s1
          \bar ""
        }
        \once \override TextScript.extra-offset = #'( 0 . -3.0 )
        \once \override TextScript.word-space = #1.5
        <>^\markup { \center-column { "D.S. al Coda" \line { \musicglyph #"scripts.coda" \musicglyph #"scripts.tenuto" \musicglyph #"scripts.coda"} } }

        % Increasing the unfold counter will expand the staff-free space
        \repeat unfold 2 {
          s1
          \bar ""
        }
        % Resume bar count and show staff lines again
     \startStaff
   \cadenzaOff
   \bar"||" a2~ a8 d, fis a | cis2. b4 |
   a4. a8 a gis fis gis | a4 a2 a8 b |
   cis4. cis8 cis cis b a | cis2. fis,4 |
   a2~ a8 d, fis a | b4 cis a b | a1 | r1 \bar"||"
  }
>>
