# Algorithm Implementation

## 01 Knapsack (Brute Force) - Mike
For this algorithm I started by looking around the internet for approaches to 01 knapsack problems in general. I remembered us covering 01 knapsack in class as part of the dynamic programming lecture but I didn't see the brute force approach demonstrated there. Luckily, there's a ton of information for 01 knapsack by other universities and tutors online so I was able to get started on this pretty quickly. When looking around I saw the word 'permutation' come up pretty frequently and to my knowledge there's no easy built in permutation generator for java. Most of the online examples and papers I was reading used Python's itertools, which was like 1 line of permutation generation. For our project though, I decided I was going write permutation generation from scratch. The easiest way I could think of was a sequence of 1s and 0s representing both the index of an item and whether or not the item at that index was in the knapsack or not (1 = in 0 = not in). This approach is EXTREMELY brute force because it's both very mechanical, not optimized at all and goes through every possible permutation. With each permutation we are filling an array with 1s and 0s based on a bitmasked number representing our current iteration and then going through that array a second time to pick our items. A much better optimization would be to just use the bitmasked value to put the item into the knapsack directly. We also do not do any validation on item permutations. If the knapsack is going to be overweight or low value we don't short circuit and move onto the next permutation. While searching around for other ways of doing this in Java I stumbled upon some recursive approach that too a divide and conquer approach. The authors of these approaches stated that this is suboptimal because it floods memory with new arrays and variables and can get out of control if you have larger knapsack capacities. It was tempting to try this but closed form functions are just easier for me to understand.

## 01 Knapsack (Greedy)
This approach is pretty straight forward. I couldn't find many interesting or unique versions of this other than what we went discussed in class. The algorithm is very simple, we just sort the set of items by value and then begin taking the highest value items until we cannot fit anything else into the knapsack. For our implementation I basically copied and pasted the selection sort algorithm from the previous project. I suppose you could optimize this further by using a better sorting algorithm but the greedy approach isn't providing a complex solution to the knapsack problem and would probably be the fastest algorithm regardless.

## 01 Knapsack (Dynamic Programming)
Even though this is the most complex to understand it wasn't the hardest to implement. I was basically able to implement this almost step by step as demonstrated in the dynamic programming lecture. I actually implemented this earlier in the quarter for the CSB 305 assignment where we needed to solve the 01 knapsack table because it was simpler than doing the math for each cell. When I looked around online  though I found several interesting approaches including some recursive ones and a space optimized solution that skips making a 2D array entirely (below).

```
   int[] dp = new int[capacity + 1];
 
   for (int i = 1; i < totalValues + 1; i++) {

       for (int w = capacity; w >= 0; w--) {
 
            if (weights[i - 1] <= w)
 
               dp[w]= Math.max(dp[w], dp[w - weights[i - 1]] + values[i - 1]);
            }
        }
        
   return dp[W];
```
It's a little difficult for me to understand why this works though because I don't understand exactly what that inner Math.max comparison is looking for. I decided to keep our implementation more mechanical than the online examples because the construction of a table and the cell by cell comparisons more closely matches what we did in class/pen & paper solution.