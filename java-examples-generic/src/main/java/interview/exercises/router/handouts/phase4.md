# Router Rule Matcher — Phase 4

**Time:** ~20 minutes. No code — draw it and talk.

Your matcher now sits in the data path.

- **500,000 packets per second**, per node
- Budget is **under 50 microseconds** per packet
- **50,000 rules**
- The rule set is **edited by admins at runtime**. A change must take effect within seconds, without dropping or misrouting a single packet
- Every rule needs a **hit counter**, readable from outside

## What to cover

- How you avoid walking 50,000 rules for every packet
- What data structure gets you there, and what it costs to build
- How a rule change lands while packets are in flight
- Whether matching takes a lock, and why not
- Where hit counters live when every core is writing to them

Name specific technologies, but be ready to say **why that one** and what you give up by choosing it. "We'd put an index in front" is a starting point, not an answer.

Don't design everything. If you're unsure whether something matters here, say so and move on.
