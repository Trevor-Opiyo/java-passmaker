import java.io.Console;
import java.security.SecureRandom;

class Main {
	private static final String upper_options = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String lower_options = "abcdefghijklmnopqrstuvwxyz";
	private static final String integer_options = "0123456789";
	private static final String special_options = "!@#$%^&*()-_+=><";

	public static void main(final String[] args) {
		final Console console = System.console();
		System.out.println("----------------------------------------\n           Password Generator\n----------------------------------------");
		String pass_options = create_string("", "upper-case letters", upper_options);
		pass_options = create_string(pass_options, "lower-case letters", lower_options);
		pass_options = create_string(pass_options, "integers", integer_options);
		pass_options = create_string(pass_options, "special characters", special_options);
		final int pass_length = accept_integer(pass_options);
		print(pass_length, pass_options);
		outerloop: while(true) {
			final String rerun = console.readLine("\nRegenerate password with same parameters? [Y/N]\n");
			switch (rerun) {
				case "Y":
				case "y":
				case "Yes":
				case "yes":
				case "YES": {
					print(pass_length, pass_options);
					break;
				}
				case "N":
				case "n":
				case "no":
				case "No":
				case "NO": {
					break outerloop;
				}
				default: {
					System.out.print("Please enter a valid input. eg.(Y,N,y,n)\n");
				}
			}
		}
	}

	public static String create_string(final String pass_options, final String char_type, final String new_options) {
		final Console console2 = System.console();
		while(true) {
			final String input = console2.readLine("Include " + char_type + "? [Y/N]\n");
			switch (input) {
				case "Y":
				case "y":
				case "yes":
				case "YES":
				case "Yes": {
					return pass_options + new_options;
				}
				case "N":
				case "n":
				case "no":
				case "NO":
				case "No": {
					return pass_options;
				}
				default: {
					System.out.print("Please enter valid input. eg.(Y,N,y,n)\n");
				}
			}
		}
	}

	public static int accept_integer(final String pass_options) {
		final Console console = System.console();
		int proper_entry;
		String entry;
		do {
			entry = console.readLine("Enter an integer length for the password.\n");
			try {
				proper_entry = Integer.parseInt(entry);
				return proper_entry;
			} catch (final Exception e) {
				System.out.print("Please enter a valid integer.\n");
			}
		} while (true);
	}

	public static void print(final int pass_length, final String pass_options) {
		final SecureRandom rgen = new SecureRandom();
		int rando;
		for (int x = 0; x < pass_length; x++) {
			rando = rgen.nextInt(pass_options.length());
			System.out.print(pass_options.charAt(rando));
		}
	}
}
