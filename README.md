# Dynamic Multilevel Caching System

## Overview
This project implements a dynamic multilevel caching system with support for eviction policies like LRU (Least Recently Used) and LFU (Least Frequently Used). The system is designed to handle multiple cache levels, data retrieval, and eviction based on the policy specified for each level.

## Features
- Multiple cache levels (L1, L2, ..., Ln)
- Eviction policies: LRU and LFU
- Dynamic addition and removal of cache levels
- Efficient data retrieval and promotion across cache levels

## How It Works
1. **Cache Levels**: Each cache level has its own size and eviction policy.
2. **Data Retrieval**: Data is retrieved from the highest cache level first. If found in a lower cache, it is promoted to the top cache.
3. **Eviction Policies**: The cache supports both LRU and LFU eviction policies.
4. **Data Insertion**: New data is always inserted into the L1 cache, with evictions handled if the cache is full.

## How to Run
1. Clone the repository.
2. Compile and run the `Main.java` class.
3. Test cases are provided in the `Main.java` class to demonstrate the functionality of the system.

## Assumptions
- All caches use the same eviction policy for simplicity.
- No external data sources are used; everything is stored in memory.

## Example
```bash
After initial insertions:
L1 Cache: {A=1, B=2, C=3}
L2 Cache: {}

Get A: 1

After inserting D (with eviction):
L1 Cache: {A=1, D=4, C=3}
L2 Cache: {B=2}

Get C: 3

Final cache state:
L1 Cache: {A=1, D=4, C=3}
L2 Cache: {B=2}
