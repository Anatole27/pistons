#!/bin/bash

notes='a aes aes_ aes__ ais ais_ ais__ a_ a__ b bes bes_ bes__ bis bis_ b_ b__ ces_ ces__ cis_ cis__ c_ c__ des_ des__ dis_ dis__ d_ d__ ees_ ees__ eis_ eis__ e_ e__ fes_ fes__ fis fis_ fis__ f_ f__ g ges ges_ ges__ gis gis_ gis__ g_ g__'

for note in $notes 
do
 notelil=`echo "${note//_/\'}"`
 echo {$notelil} > li.ly
 lilypond li.ly -o li.pdf
 convert -define profile:skip=ICC -density 150 li.pdf -quality 90 ../img/$note.png
done
