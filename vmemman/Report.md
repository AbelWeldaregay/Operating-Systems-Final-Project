# Report: Virtual Memory Management problem

This program simulates the following page replacement algorithms:
- **First In First Out (FIFO)**
  - When a page fault occurs, the page that has been in memory the longest is replaced
- **Least Recently Used (LRU)**
  - When a page fault occurs, the least recently used page in memroy is replaced
- **Most Recently Used (MRU)**
  - When a page fault occurs, the most recently used page in memory is replaced
- **Optimal**
  - When a page fault occurs, it looks forward in time to find the page to replace
    
When reviewing the results from the [results doc](https://github.com/AbelWeldaregay/Operating-Systems-Final-Project/blob/master/vmemman/vmemman_output_example.txt), you can see that there is a pattern for every page replacement algorithm, which is that as the number of frames increase, percentage of page faults decreases. This means that it is having better performance because less page faults mean more hits resulting in better performance.

