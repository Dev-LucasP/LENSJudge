
package lensjudge.main;

import java.io.File;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: lensjudge <sourceFile> <test.in> <test.ans>");
			return;
		}

		String sourceFile = new File(args[0]).getAbsolutePath(); // Convertir en chemin absolu
		String inputFilePath = new File(args[1]).getAbsolutePath(); // Chemin absolu
		String expectedOutputFilePath = new File(args[2]).getAbsolutePath(); // Chemin absolu

		try {
			Runner runner = RunnerBuilder.createRunner(sourceFile, inputFilePath, expectedOutputFilePath).build();
			String result = runner.run();

			System.out.println(result);

		} catch (IOException | InterruptedException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
