% Function to add userdata and preferences.

function [data, pref] = addInfo(data, pref, first, last)
    % Password confirmation variables.
    pass = "not";
    pass2 = "done";

    % Prompts user to enter a password and confirm.
    while ~strcmp(pass,pass2)
        pass = input('Enter a password: ',"s");
        pass2 = input('Reenter password: ',"s");
    end

    % Asks for personal traits.
    fprintf("Personal traits: \n");
    age = input("Enter age: ");
    gender = input("Enter gender: (m/f)","s");
    pet = input("What pet do you need taken care of? (N/A if none): ", "s");
    language = input("Enter language: ", "s");
    
    % Asks for preferences.
    fprintf("\nPreferences: \n");
    prefAge = input("Minimum age of caretaker: ");
    prefGen = input("Preferred gender of caretaker (m/f/[N/A]): ", "s");

    % Compiles answers and adds it to the data tables.
    prof = {last, first, age, gender, pet, language, 0};
    prefProf = {last, first, pass, prefAge, prefGen, pet, "N/A", "N/A"};
    data = [data; prof];
    pref = [pref; prefProf];
end