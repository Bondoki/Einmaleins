 
set style data fsteps
set xlabel "Date\nTime"
set timefmt "%Y-%m-%dT%H:%M:%S"
set yrange [ 0 : ]
set xdata time
set xrange [ "2018-10-05T00:00:00":"2018-12-31T24:00:00" ]
set ylabel "time [s]"
set format x "%d/%m\n%H:%M"
set grid
set key left
set style line 1 lc rgb '#0060ad' lt 1 lw 2 pt 7 pi -1 ps 1.5
#set pointintervalbox 3

plot 'Training.txt' using 1:2 with linespoints ls 1

pause -1
