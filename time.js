window.onload = function() {

function updateClock() {
    var now = new Date(), // current date
        months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']; 
	h = now.getHours(); 
	m = now.getMinutes(); 
	s = now.getSeconds(); 
	m = addZero(m);
	s = addZero(s);
        time = h + ':' + m + ':' + s;

        // a cleaner way than string concatenation
        date = [now.getDate(),
                months[now.getMonth()],
                now.getFullYear()].join(' ');

    // set the content of the element with the ID time to the formatted string
    document.getElementById('time').innerHTML = [date, time].join(' / ');

    // call this function again in 1000ms
    setTimeout(updateClock, 1000);
}

function addZero(i){
	if(i < 10) { i ="0" + i};
	return i;
} 

updateClock(); // initial
}
