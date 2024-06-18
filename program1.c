#include <stdio.h>
int main()
{
   char str[12];
   printf("Enter a string: ");
   scanf("%[^\n]%*c", str);

   int length = 0;
   while (str[length] != '\0')
      length++;

   char strAnd[length + 1];
   char strXor[length + 1];

   for (int i = 0; i < length; i++)
   {
      strAnd[i] = str[i] & 127;
      strXor[i] = str[i] ^ 127;
   }
   strAnd[length] = '\0'; 
   strXor[length] = '\0';

   printf("The input string is: %s\n", str);
   printf("The AND result: %s\n", strAnd);
   printf("The XOR result: %s\n", strXor);

   return 0;
}
