// getting the user choice:
const getUserSelection = userInput =>{
    userInput = userInput.toLowerCase();
    if (userInput === 'rock' || userInput==='paper' || userInput ==='scissors') {
        return userInput;
    } else {
        console.log('not a valid choice');
    }
};
// Creating an random computer choice:
function getComputerSelection() {
    switch(Math.floor(Math.random()*3)) {
        case 0:
            return 'paper';
            break;
        case 1:
            return 'rock';
            break;
        case 2:
            return 'scissors';
            break;
    };
}
// determining the winner between the choice:
function determineWinner(userSelection,computerSelection) {
    if (userSelection === computerSelection) {
        return 'It\'s a tie!';
    } else if (userSelection === 'rock') {
        if (computerSelection === 'paper') {
            return 'Computer wins!';
        } else {
            return 'You win!';
        }
    } else if (userSelection === 'paper'){
        if (computerSelection === 'scissors') {
            return 'Computer wins!';
        }else {
            return 'You win!';
        }
    } else if (userSelection === 'scissors') {
        if (computerSelection === 'rock') {
            return 'Computer wins!';
        } else {
            return 'You win!';
        }
    }
};
// Calling the playGame function:
function playGame(Selection) {
    var userSelection = getUserSelection(Selection);
    var computerSelection = getComputerSelection()
    document.getElementById('user-selection').innerHTML = `User Selected : ${userSelection}`;   
    document.getElementById('computer-selection').innerHTML = `Computer Selected : ${computerSelection}`;   
    document.getElementById('final-result').innerHTML = determineWinner(userSelection, computerSelection); 
};