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
