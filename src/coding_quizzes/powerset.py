import pprint

pp = pprint.PrettyPrinter(indent=2)

inputs = ["apple", "orange", "pear"]

"""
BAD IMPLEMENTATION. do not use it
"""


def all_combination(xs):
    possibilities = [xs] + [
        x
        for inner in [all_combination(xs[:i] + xs[i + 1 :]) for i, _ in enumerate(xs)]
        for x in inner
    ]
    # dedupe step
    ys = []
    [ys.append(p) for p in possibilities if p not in ys]
    return ys


pp.pprint(all_combination(inputs))
