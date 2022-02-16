import Foundation

@objc public class SimpleSim: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
