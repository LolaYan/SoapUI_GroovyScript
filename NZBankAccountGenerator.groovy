context.setProperty("BankAccountBase", new BankAccountBase());
public class BankAccountBase {
	public static String BankIDStr;
	public static String BankBranchStr;
	public static String AlgorithmType;
	public static String AlgorithmMod;
	public static String checkDigit;
	public static String BankAccountBaseStr;
	public static String suffixStr;
	public static String BankAccountStr;
	public static int[] algorithmWeight;

	public static String accountBaseLengthSetting = "7";
	public static String suffixSetting = "000";

	public static int RandomNumber(int min, int max) {
		int res = (int) (min + (Math.random() * (max - min)));
		return res;
	}

	public static String PaddingLeftWithZero(int num, int length) {
		def valueStr=num.toString();
    		def res=valueStr.padLeft(length, '0');
    		return res;
		
	}

	public static String PaddingLeftWithZero(String num, int length) {
		
		def res=num.padLeft(length, '0');
    		return res;
	}

	public static String doMod() {
		checkDigit = "N/A"
		while (checkDigit.equals("N/A"))
		{
			getBankAccountBaseStr();

			String tempBankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr;
			// validate the length of BankAccountStr is 2+4+7 = 13
			// get AlgorithmWeight
			algorithmWeight = GetAlgorithmWeight(AlgorithmType);
			if (AlgorithmType.equals("A") || AlgorithmType.equals("B")
					|| AlgorithmType.equals("C") || AlgorithmType.equals("D")) {

				checkDigit = GetCheckDigit_Mod11(AlgorithmType,
						tempBankAccountStr, algorithmWeight);
			} else if (AlgorithmType.equals("E")) {
				tempBankAccountStr = tempBankAccountStr + suffixStr;
				checkDigit = GetCheckDigit_Mod11(AlgorithmType,
						tempBankAccountStr, algorithmWeight);
			} else if (AlgorithmType.equals("F")) {
				checkDigit = GetCheckDigit_Mod10(AlgorithmType,
						tempBankAccountStr, algorithmWeight);
			} else if (AlgorithmType.equals("G")) {
				tempBankAccountStr = tempBankAccountStr + suffixStr;
				checkDigit = GetCheckDigit_Mod10(AlgorithmType,
						tempBankAccountStr, algorithmWeight);
			} else if (AlgorithmType.equals("X")) {
				AlgorithmMod = "Mod 1";
				checkDigit = Integer.toString(RandomNumber(0, 9));
			}

			if(checkDigit.equals("N/A") == false) break
		}

		if (AlgorithmType.equals("E") || AlgorithmType.equals("G")) {

			suffixStr = suffixStr + checkDigit;
			//get the last digit of suffixStr
			suffixStr = suffixStr.substring(suffixStr.length()-3,suffixStr.length())
		} else {
			BankAccountBaseStr = BankAccountBaseStr + checkDigit;
			checkSuffixSetting();
		}

		checkAccountBaseLengthSetting();
		BankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr + suffixStr;

		return BankAccountStr;
	}

	// If AlgorithmType is E or G, return the account base and first 3 digit of
	// suffix.
	// If AlgorithmType is others, return first 7 digit of the account base and
	// first 3 digit of suffix.
	public static void getBankAccountBaseStr() {
		int ranNoBankAccountBase;
		int ranNoSuffix;

		if (AlgorithmType.equals("E") || AlgorithmType.equals("G")) {
			ranNoBankAccountBase = RandomNumber(0, 9999999);
			BankAccountBaseStr = PaddingLeftWithZero(ranNoBankAccountBase, 8);

			ranNoSuffix = RandomNumber(0, 99);
			suffixStr = PaddingLeftWithZero(ranNoSuffix, 3);

		} else {
			ranNoBankAccountBase = RandomNumber(0, 999999);
			BankAccountBaseStr = PaddingLeftWithZero(ranNoBankAccountBase, 7);

			ranNoSuffix = RandomNumber(0, 9999);
			suffixStr = PaddingLeftWithZero(ranNoSuffix, 4);
		}

		setAlgorithmBasedonAccountBase(ranNoBankAccountBase);
		// After setAlgorithmBasedonAccountBase(), the AlgorithmType should be
		// set up.

	}

	public static void setAlgorithmBasedonAccountBase(int accountBase) {
		if (AlgorithmType.equals("Unkown")) {
			if (accountBase < 99000) {
				AlgorithmType = "A";

			} else {
				AlgorithmType = "B";
			}
		}
	}

	public static String getBankID() {
		String[] BankIDArr = [ "01", "02", "03", "06", "08", "09", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22",
				"23", "24", "25", "26", "27", "28", "29", "30", "31", "33",
				"35", "38" ];
		Random random = new Random();
		int index = RandomNumber(0, BankIDArr.length - 1);
		BankIDStr = BankIDArr[index];
		return BankIDStr;
	}

	public static String getBankID(String type) {
		switch (type) {
		case "A":
			BankIDStr = getBankID_AB();
			break;
		case "B":
			BankIDStr = getBankID_AB();
			break;
		case "D":
			BankIDStr = getBankID_D();
			break;
		case "E":
			BankIDStr = getBankID_E();
			break;
		case "F":
			BankIDStr = getBankID_F();
			break;
		case "G":
			BankIDStr = getBankID_G();
			break;
		case "X":
			BankIDStr = getBankID_X();
			break;
		default:
			BankIDStr = getBankID_AB();
			break;
		}
		return BankIDStr;
	}

	public static String getBankID_AB() {
		String[] BankIDArr = [ "01", "02", "03", "06", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"27", "30", "35", "38" ];
		Random random = new Random();
		int index = RandomNumber(0, BankIDArr.length - 1);
		BankIDStr = BankIDArr[index];
		return BankIDStr;
	}

	public static String getBankID_D() {
		BankIDStr = "08";
		return BankIDStr;
	}

	public static String getBankID_E() {
		BankIDStr = "09";
		return BankIDStr;
	}

	public static String getBankID_F() {
		String[] BankIDArr = [ "25", "33" ];
		Random random = new Random();
		int index = RandomNumber(0, BankIDArr.length - 1);
		BankIDStr = BankIDArr[index];
		return BankIDStr;
	}

	public static String getBankID_G() {
		String[] BankIDArr = [ "26", "28", "29" ];
		Random random = new Random();
		int index = RandomNumber(0, BankIDArr.length - 1);
		BankIDStr = BankIDArr[index];
		return BankIDStr;
	}

	public static String getBankID_X() {
		BankIDStr = "31";
		return BankIDStr;
	}

	public static String getBankBranch(String BankIDStr) {
		int BankBranch;
		String BankBranchStr;
		// String BankIDStr = PaddingLeftWithZero(BankID,2);
		int i;
		AlgorithmType = "Unkown";
		switch (BankIDStr) {
		case "01":
			i = RandomNumber(1, 3);
			if (i == 1) {
				BankBranch = RandomNumber(1, 999);
			} else if (i == 2) {
				BankBranch = RandomNumber(1100, 1199);
			} else {
				BankBranch = RandomNumber(1800, 1899);
			}
			break;
		case "02":
			i = RandomNumber(1, 2);
			if (i == 1) {
				BankBranch = RandomNumber(1, 999);
			} else {
				BankBranch = RandomNumber(1200, 1299);
			}
			break;
		case "03":
			i = RandomNumber(1, 5);
			if (i == 1) {
				BankBranch = RandomNumber(1, 999);
			} else if (i == 2) {
				BankBranch = RandomNumber(1300, 1399);
			} else if (i == 3) {
				BankBranch = RandomNumber(1500, 1599);
			} else if (i == 4) {
				BankBranch = RandomNumber(1700, 1799);
			} else {
				BankBranch = RandomNumber(1900, 1999);
			}
			break;
		case "06":
			i = RandomNumber(1, 2);
			if (i == 1) {
				BankBranch = RandomNumber(1, 999);
			} else {
				BankBranch = RandomNumber(1400, 1499);
			}
			break;
		case "08":
			BankBranch = RandomNumber(6500, 6599);
			AlgorithmType = "D";
			break;
		case "09":
			BankBranch = 0;
			AlgorithmType = "E";
			break;
		case "11":
			i = RandomNumber(1, 2);
			if (i == 1) {
				BankBranch = RandomNumber(5000, 6499);
			} else {
				BankBranch = RandomNumber(6600, 8999);
			}
			break;
		case "12":
			i = RandomNumber(1, 3);
			if (i == 1) {
				BankBranch = RandomNumber(3000, 3299);
			} else if (i == 2) {
				BankBranch = RandomNumber(3400, 3499);
			} else {
				BankBranch = RandomNumber(3600, 3699);
			}
			break;
		case "13":
			BankBranch = RandomNumber(4900, 4999);
			break;
		case "14":
			BankBranch = RandomNumber(4700, 4799);
			break;
		case "15":
			BankBranch = RandomNumber(3900, 3999);
			break;
		case "16":
			BankBranch = RandomNumber(4400, 4499);
			break;
		case "17":
			BankBranch = RandomNumber(3300, 3399);
			break;
		case "18":
			BankBranch = RandomNumber(3500, 3599);
			break;
		case "19":
			BankBranch = RandomNumber(4600, 4649);
			break;
		case "20":
			BankBranch = RandomNumber(4100, 4199);
			break;
		case "21":
			BankBranch = RandomNumber(4800, 4899);
			break;
		case "22":
			BankBranch = RandomNumber(4000, 4049);
			break;
		case "23":
			BankBranch = RandomNumber(3700, 3799);
			break;
		case "24":
			BankBranch = RandomNumber(4300, 4399);
			break;
		case "25":
			BankBranch = RandomNumber(2500, 2599);
			AlgorithmType = "F";
			break;
		case "26":
			BankBranch = RandomNumber(2600, 2699);
			AlgorithmType = "G";
			break;
		case "27":
			BankBranch = RandomNumber(3800, 3849);
			break;
		case "28":
			BankBranch = RandomNumber(2100, 2149);
			AlgorithmType = "G";
			break;
		case "29":
			BankBranch = RandomNumber(2150, 2299);
			AlgorithmType = "G";
			break;
		case "30":
			BankBranch = RandomNumber(2900, 2949);
			break;
		case "31":
			BankBranch = RandomNumber(2800, 2849);
			AlgorithmType = "X";
			break;
		case "33":
			BankBranch = RandomNumber(6700, 6799);
			AlgorithmType = "F";
			break;
		case "35":
			BankBranch = RandomNumber(2400, 2499);
			break;
		case "38":
			BankBranch = RandomNumber(9000, 9499);
			break;
		default:
			BankIDStr = "01";
			BankBranch = RandomNumber(1, 999);
			break;

		}

		BankBranchStr = PaddingLeftWithZero(BankBranch, 4);
		return BankBranchStr;
	}

	public static int getBankAccountBasePartially() {
		int BankAccountBase1 = RandomNumber(0, 9999999);
		// int BankAccountBase2 = RandomNumber(0, 99999999999); // only for
		// Algorithm E and G
		return BankAccountBase1;
	}

	public static int[] GetAlgorithmWeight(String AlgorithmType) {

		Integer[] WeightNumberArr = [ 0, 0, 6, 3, 7, 9, 0, 0, 10, 5, 8,
				4, 2, 1, 0, 0, 0, 0 ];
		switch (AlgorithmType) {
		case "A":
			Integer[]  WeightNumberArrA = [ 0, 0, 6, 3, 7, 9, 0, 0, 10, 5,
					8, 4, 2, 1, 0, 0, 0, 0 ];
			WeightNumberArr = WeightNumberArrA;
			break;
		case "B":
			Integer[]  WeightNumberArrB = [ 0, 0, 0, 0, 0, 0, 0, 0, 10, 5,
					8, 4, 2, 1, 0, 0, 0, 0 ];
			WeightNumberArr = WeightNumberArrB;
			break;
		case "C":
			Integer[]  WeightNumberArrC = [ 3, 7, 0, 0, 0, 0, 9, 1, 10, 5,
					3, 4, 2, 1, 0, 0, 0, 0 ];
			WeightNumberArr = WeightNumberArrC;
			break;
		case "D":
			Integer[]  WeightNumberArrD = [ 0, 0, 0, 0, 0, 0, 0, 7, 6, 5,
					4, 3, 2, 1, 0, 0, 0, 0 ];
			WeightNumberArr = WeightNumberArrD;
			break;
		case "E":
			Integer[]  WeightNumberArrE = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					5, 4, 3, 2, 0, 0, 0, 1 ];
			WeightNumberArr = WeightNumberArrE;
			break;
		case "F":
			Integer[]  WeightNumberArrF = [ 0, 0, 0, 0, 0, 0, 0, 1, 7, 3,
					1, 7, 3, 1, 0, 0, 0, 0 ];
			WeightNumberArr = WeightNumberArrF;
			break;
		case "G":
			Integer[]  WeightNumberArrG = [ 0, 0, 0, 0, 0, 0, 0, 1, 3, 7,
					1, 3, 7, 1, 0, 3, 7, 1 ];
			WeightNumberArr = WeightNumberArrG;
			break;
		case "X":
			Integer[]  WeightNumberArrX = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
					0, 0, 0, 0, 0, 0, 0, 0 ];
			WeightNumberArr = WeightNumberArrX;
			break;
		default:
			break;

		}
		return WeightNumberArr;
	}

	public static String GetCheckDigit_Mod11(String AlgorithmType,
			String number, int[] WeightNumberArr) {

		// Only AlgorithmType A, B, C, D, E are accepted here
		int sum = 0;
		int digitSum = 0;
		int digitValue = 0;
		int weightValue = 0;
		int accountLength;

		AlgorithmMod = "Mod11";

		if (AlgorithmType.equals("E")) {
			accountLength = 17;
		} else {
			accountLength = 13;
		}

		for (int i = 0; i < accountLength; i++) {
			digitValue = (int) Character.getNumericValue(number.charAt(i));
			weightValue = WeightNumberArr[i];
			digitSum = digitValue * weightValue;
			sum += digitSum;
			System.out.println("No: " + i + " digitValue: " + digitValue
					+ " * weightValue: " + weightValue + " = digitSum: "
					+ digitSum + " sum:" + sum);
		}
		int modNo = 11;
		int mod = (sum % modNo);
		if (mod == 0) {
			return "0";
		} else if (mod == 1) {
			return "N/A";
		} else {

			return Integer.toString(modNo - mod);
		}
	}

	public static String GetCheckDigit_Mod10(String AlgorithmType,
			String number, int[] WeightNumberArr) {
		// Only AlgorithmType F, G are accepted here
		int sum = 0;
		int digitSum = 0;
		int digitValue = 0;
		int weightValue = 0;
		int accountLength;

		AlgorithmMod = "Mod10";

		if (AlgorithmType.equals("G")) {
			accountLength = 17;
		} else {
			accountLength = 13;
		}

		for (int i = 0; i < accountLength; i++) {
			digitValue = (int) Character.getNumericValue(number.charAt(i));
			weightValue = WeightNumberArr[i];
			digitSum = digitValue * weightValue;
			sum += digitSum;
			System.out.println("No: " + i + " digitValue: " + digitValue
					+ " * weightValue: " + weightValue + " = digitSum: "
					+ digitSum + " sum:" + sum);
		}
		int modNo = 10;
		int mod = (sum % modNo);
		if (mod == 0) {
			return "0";
		} else {

			return Integer.toString(modNo - mod);
		}
	}

/*
	public static void printInfo() {
		System.out.println("BankIDStr: " + BankIDStr);
		System.out.println("BankBranchStr: " + BankBranchStr);
		System.out.println("BankAccountBaseStr: " + BankAccountBaseStr);
		System.out.println("suffixStr: " + suffixStr);
		System.out.println("AlgorithmType: " + AlgorithmType);
		// System.out.println("algorithmWeight: " + String.Join(",",
		// algorithmWeight));
		System.out.println("AlgorithmMod: " + AlgorithmMod);
		System.out.println("checkDigit: " + checkDigit);
		System.out.println("BankAccountStr: " + BankIDStr + "-" + BankBranchStr
				+ "-" + BankAccountBaseStr + "-" + suffixStr);
		System.out.println("BankAccountStr: " + BankAccountStr);
		System.out.println("BankAccountStr length: " + BankAccountStr.length());
		System.out.println("");

	}
*/
	public static void checkAccountBaseLengthSetting() {
		if (accountBaseLengthSetting.equals("7")) {
			BankAccountBaseStr = BankAccountBaseStr.substring(1);
		}
	}

	public static void checkSuffixSetting() {
		// Not applied to Algorithm E and G
		if (suffixSetting.equals("R4")) {
		} else if (suffixSetting.equals("R3")) {
			suffixStr = suffixStr.substring(1);
		} else if (suffixSetting.equals("0000")) {
			suffixStr = "0000";
		} else if (suffixSetting.equals("000")) {
			suffixStr = "000";
		}
	}
	/**/
    //getBankAccount
    public static String getBankAccount()
    {
        //1. get BankID
        BankIDStr = getBankID();
        //2. get BankBranch
        //Based on BankID, filter the AlgorithmType of D, E, E, G, X, F
        BankBranchStr = getBankBranch(BankIDStr);
        doMod();
        return BankAccountStr;
    }

    public static String getBankAccountA()
    {
        //1. get BankID
        BankIDStr = getBankID_AB();
        //2. get BankBranch
        //Based on BankID, filter the AlgorithmType of D, E, E, G, X, F
        BankBranchStr = getBankBranch(BankIDStr);
        
        suffixStr = PaddingLeftWithZero(RandomNumber(0, 9999), 4);
        AlgorithmType = "A";
        checkDigit = "N/A";
        while (checkDigit.equals("N/A")){        
            BankAccountBaseStr = PaddingLeftWithZero(RandomNumber(0, 98999), 7);
            String tempBankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr;
            algorithmWeight = GetAlgorithmWeight(AlgorithmType);
            checkDigit = GetCheckDigit_Mod11(AlgorithmType, tempBankAccountStr, algorithmWeight);                
            if(checkDigit.equals("N/A") == false) break
        } 
        BankAccountBaseStr = BankAccountBaseStr + checkDigit;
        checkSuffixSetting();
        checkAccountBaseLengthSetting();
        BankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr + suffixStr;
        //printInfo();
        return BankAccountStr;
    }

    public static String getBankAccountB()
    {
        //1. get BankID
        BankIDStr = getBankID_AB();
        //2. get BankBranch
        //Based on BankID, filter the AlgorithmType of D, E, E, G, X, F
        BankBranchStr = getBankBranch(BankIDStr);

        suffixStr = PaddingLeftWithZero(RandomNumber(0, 9999), 4);
        AlgorithmType = "B";
        checkDigit = "N/A";
        while (checkDigit.equals("N/A"))
        {
            BankAccountBaseStr = PaddingLeftWithZero(RandomNumber(99000, 999999), 7);
            String tempBankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr;
            algorithmWeight = GetAlgorithmWeight(AlgorithmType);
            checkDigit = GetCheckDigit_Mod11(AlgorithmType, tempBankAccountStr, algorithmWeight);
            if(checkDigit.equals("N/A") == false) break
        } 
        BankAccountBaseStr = BankAccountBaseStr + checkDigit;
        checkSuffixSetting();
        checkAccountBaseLengthSetting();
        BankAccountStr = BankIDStr + BankBranchStr + BankAccountBaseStr + suffixStr;
        //printInfo();
        return BankAccountStr;
    }

    public static String getBankAccountByType(String type)
    {
        switch (type)
        {
            case "A":
                BankAccountStr = getBankAccountA();
                break;
            case "B":
                BankAccountStr = getBankAccountB();
                break;
            default:
                BankIDStr = getBankID(type);
                BankBranchStr = getBankBranch(BankIDStr);
                doMod();
                break;
        }
           //printInfo();
        return BankAccountStr;
    }
	/*
	public static void main(String[] args) {
		String accountStr1 = getBankAccountByType("C");
		String accountStrA = getBankAccountA();
		String accountStrB = getBankAccountB();
		//String accountStr = getBankAccount();
	}
	*/

}

def bankAccount=context.BankAccountBase.getBankAccount();
log.info bankAccount;
testRunner.testCase.setPropertyValue( 'acctNumber',bankAccount);